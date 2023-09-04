import React, { useState, FormEvent, ChangeEvent, useEffect, useContext } from "react"
import { useNavigate } from "react-router-dom"
import { dbApiWithoutAuth } from "../../httpService/httpService"
import { AxiosResponse, AxiosError } from "axios"
import { Role } from "../../models/Role"
import { Department } from "../../models/Department"
import { UserContext } from "../../context/UserContext"

type RegistrationData = {
  name: string
  email: string
  password: string
  repeatPassword: string
  role: Role
  department: Department
}

type RegistrationResponse = {
  accessToken: string;
  refreshToken: string;
};

export const Registration: React.FC = () => {
  const [regFormData, setRegFormData] = useState<RegistrationData>({
    name: "",
    email: "",
    password: "",
    repeatPassword: "",
    role: { roleId: 0, roleName: "" },
    department: { departmentId: 0, departmentName: "" },
  })
  const { setUser } = useContext(UserContext);
  const [roles, setRoles] = useState<Role[]>([])
  const [departments, setDepartments] = useState<Department[]>([])
  const [error, setError] = useState<string | null>(null)
  const navigate = useNavigate()


  async function fetchRolesAndDepartments() {
    try {
      const getRoles = dbApiWithoutAuth.get("/roles/")
      const getDepartments = dbApiWithoutAuth.get("/departments/")

      const results = await Promise.all([getRoles, getDepartments])
      setRoles(results[0].data)
      setDepartments(results[1].data)
    } catch (error) {
      console.error(error)
    }
  }

  useEffect(() => {
    fetchRolesAndDepartments()
    console.log("pobrano dane")
  }, [])

  function handleRoleChange(event: ChangeEvent<HTMLSelectElement>) {
    const selectedRoleId = event.target.value

    const selectedRole = roles.find(
      (role) => role.roleId.toString() === selectedRoleId
    )
    if (selectedRole) {
      setRegFormData({ ...regFormData, role: selectedRole })
    }
  }

  function handleDepartmentChange(event: ChangeEvent<HTMLSelectElement>) {
    const selectedDepartmentId = event.target.value;

    const selectedDepartment = departments.find(
      (department) =>
        department.departmentId.toString() === selectedDepartmentId
    );
    if (selectedDepartment) {
      setRegFormData({ ...regFormData, department: selectedDepartment });
    }
  }

  function handleRegistrationInput(event: ChangeEvent<HTMLInputElement>) {
    const { name, value } = event.target;
    setRegFormData({ ...regFormData, [name]: value });
  }

  const register = async (url: string, data: any) => {
    try {
      const response: AxiosResponse<RegistrationResponse> =
        await dbApiWithoutAuth.post(url, data)
      
      const statusCode = response.status
      console.log("Response Status Code:", statusCode)
      return response.data
    } catch (error) {
      const axiosError = error as AxiosError;
      if (axiosError.response) {
        const errorStatusCode = axiosError.response.status
        console.log("Error Status Code:", errorStatusCode)

        throw axiosError.response.data
      } else {
        throw axiosError.message
      }
    }
  };
  async function handleRegistrationSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    if (regFormData.password !== regFormData.repeatPassword) {
      setError('Password and repeat password do not match')
      return
    }
    try {
      const responseData = await register("/api/v1/auth/register", regFormData)
      console.log("Response: ", responseData);
      const token = responseData.accessToken
      console.log(token)
      if (token) {
        sessionStorage.setItem("token", JSON.stringify(token))
        setUser(token)
      }

      setRegFormData({
        name: "",
        email: "",
        password: "",
        repeatPassword: "",
        role: { roleId: 0, roleName: "" },
        department: { departmentId: 0, departmentName: "" },
      });
      navigate("/")
    } catch (error) {
      console.error("Error:", error)
      setError("Registration failed. Please try again.")
    }
  }

  return (
    <main className="register-form">
      <form
        className="register-form__container"
        onSubmit={handleRegistrationSubmit}
      >
        <label htmlFor="name" className="register-form__label">
          User name:
        </label>
        <input
          type="text"
          name="name"
          placeholder="User name"
          className="register-form__input"
          onChange={handleRegistrationInput}
        ></input>
        <label htmlFor="email" className="register-form__label">
          Email:
        </label>
        <input
          type="email"
          name="email"
          placeholder="E-mail"
          className="register-form__input"
          onChange={handleRegistrationInput}
        ></input>
        <label htmlFor="password" className="register-form__label">
          Password:
        </label>
        <input
          type="password"
          name="password"
          placeholder="Password"
          className="register-form__input"
          onChange={handleRegistrationInput}
        ></input>
        <label htmlFor="repeat-password" className="register-form__label">
          Repeat password:
        </label>
        <input
          type="password"
          name="repeatPassword"
          placeholder="Repeat Password"
          className="register-form__input"
          onChange={handleRegistrationInput}
        ></input>

        <div className="register-form__select-container">
          <label htmlFor="role" className="register-form__label">
            Role:
          </label>
          <select
            id="role"
            name="role"
            className="register-form__select"
            onChange={handleRoleChange}
          >
            <option value="">Select a role</option>
            {roles.map((role) => (
              <option key={role.roleId} value={role.roleId.toString()}>
                {role.roleName}
              </option>
            ))}
          </select>
        </div>
        <div className="register-form__select-container">
          <label htmlFor="department" className="register-form__label">
            Department:
          </label>
          <select
            id="department"
            name="department"
            className="register-form__select"
            onChange={handleDepartmentChange}
          >
            <option value="">Select a department</option>
            {departments.map((department) => (
              <option
                key={department.departmentId}
                value={department.departmentId.toString()}
              >
                {department.departmentName}
              </option>
            ))}
          </select>
        </div>

        {/* Display error message  */}
        {error && <div className="register-form__error">{error}</div>}

        <button className="register-form__button" type="submit">
          Register account
        </button>
      </form>
      <div className="register-form__links">
        <a href="/login">Already have an account? Sign-in</a>
      </div>
    </main>
  )
}
