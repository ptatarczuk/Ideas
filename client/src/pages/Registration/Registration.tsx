import React, {
  useState,
  FormEvent,
  ChangeEvent,
  useEffect,
  useContext,
  ReactNode,
} from "react";
import { useNavigate } from "react-router-dom";
import { dbApiWithoutAuth } from "../../httpService/httpService";
import { AxiosResponse, AxiosError } from "axios";
import { Role } from "../../models/Role";
import { Department } from "../../models/Department";
import { UserContext } from "../../context/UserContext";
import jwt_decode from "jwt-decode";
import logo from "../../assets/logo.svg";
import "./Registration.css";
import { styled } from "@mui/system";
import {
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
  TextField,
} from "@mui/material";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";

type RegistrationData = {
  name: string;
  email: string;
  password: string;
  repeatPassword: string;
  role: Role;
  department: Department;
};

type RegistrationResponse = {
  accessToken: string;
  refreshToken: string;
};

type FileUpload = {
  file: File | null;
};

type FileUploadResponse = {
  fileName: string;
  size: number;
  downloadUri: string;
};

const RegisterTextField = styled(TextField)`
  background-color: #ffffff;
  border: none;
  border-radius: 69px;
  border-color: #ffffff;
  opacity: 1;
  width: 406.75px;
  height: 49.25px;
  margin-left: 30px;
  margin-top: 2%;

  &:hover {
    background-color: #ffffff;
  }

  & .MuiInputLabel-root {
    margin-left: 50px;
    margin-top: -5px;
    font-family: "Poppins Light", sans-serif;
  }

  & .MuiInputBase-input {
    margin-left: 70px;
    font-size: 16px;
    color: #807d7d;
    margin-top: -5px;
    font-family: "Poppins Light", sans-serif;
  }
`;

const RegisterSelect = styled(Select)`
  background-color: #ffffff;
  border: none;
  border-radius: 69px;
  border-color: #ffffff;
  opacity: 1;
  width: 406.75px;
  height: 49.25px;
  margin-top: 2%;
  & .MuiInputBase-input {
    margin-left: 50px;
    font-size: 16px;
    color: #807d7d;
    margin-top: 5px;
    font-family: "Poppins Light", sans-serif;
  }
`;

const RegisterSelectLabel = styled(InputLabel)`
  font-family: "Poppins Light", sans-serif;
`;

const RegisterSelectMenuItem = styled(MenuItem)`
  font-family: "Poppins Light", sans-serif;
`;

const RegisterButton = styled(Button)`
  background-color: #000000 !important;
  color: #ff8900 !important;
  text-transform: none;
  border-radius: 69px !important;
  width: 201px;
  height: 56.75px;
  margin-right: 1.5%;
  margin-bottom: 20px;
  margin-top: 10px;
  font-family: "Poppins Light", sans-serif;

  &:hover {
    background-color: #ffffff !important;
    color: #000000 !important;
  }

  .MuiButtonBase-root {
    background-color: #000000;
    color: #ff8900;
    text-transform: none;
    border-radius: 10px;
    font-family: "Poppins Light", sans-serif;
  }

  .MuiButton-root {
    font-family: "Poppins Light", sans-serif;
  }
`;

const LinkText = styled("a")`
  text-decoration: none;
  color: #ffffff;
  cursor: pointer;
  font-size: 11.5px;
  font-family: "Poppins Light", sans-serif;
  min-width: 240px;
`;

const SignInText = styled("span")`
  color: #000000;
  font-family: "Poppins Bold", sans-serif;
`;

export const Registration: React.FC = () => {
  const [regFormData, setRegFormData] = useState<RegistrationData>({
    name: "",
    email: "",
    password: "",
    repeatPassword: "",
    role: { roleId: 0, roleName: "" },
    department: { departmentId: 0, departmentName: "" },
  });
  const { setUser } = useContext(UserContext);
  const [roles, setRoles] = useState<Role[]>([]);
  const [departments, setDepartments] = useState<Department[]>([]);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();
  const [file, setFile] = useState<FileUpload | null>(null);

  async function fetchRolesAndDepartments() {
    try {
      const getRoles = dbApiWithoutAuth.get("/roles/");
      const getDepartments = dbApiWithoutAuth.get("/departments/");

      const results = await Promise.all([getRoles, getDepartments]);
      setRoles(results[0].data);
      setDepartments(results[1].data);
    } catch (error) {
      console.error(error);
    }
  }

  useEffect(() => {
    fetchRolesAndDepartments();
  }, []);

  function handleSelectChange(
    event: SelectChangeEvent<unknown>,
    child: ReactNode
  ) {
    const { name, value } = event.target;
    const selected =
      name === "role"
        ? roles.find((role) => role.roleId.toString() === value)
        : departments.find(
            (department) => department.departmentId.toString() === value
          );

    if (selected) {
      setRegFormData({ ...regFormData, [name]: selected });
    }
  }

  function handleRegistrationInput(event: ChangeEvent<HTMLInputElement>) {
    const { name, value } = event.target;
    setRegFormData({ ...regFormData, [name]: value });
  }

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const fileInput = e.target;
    if (fileInput && fileInput.files && fileInput.files[0]) {
      const file = fileInput.files[0] as File;
      setFile({ file: file });
      console.log(file);
    }
  };

  const register = async (url: string, data: any) => {
    try {
      const response: AxiosResponse<RegistrationResponse> =
        await dbApiWithoutAuth.post(url, data);
      const statusCode = response.status;
      console.log("Response Status Code:", statusCode);
      return response.data;
    } catch (error) {
      const axiosError = error as AxiosError;
      if (axiosError.response) {
        const errorStatusCode = axiosError.response.status;
        console.log("Error Status Code:", errorStatusCode);

        throw axiosError.response.data;
      } else {
        throw axiosError.message;
      }
    }
  };

  const sendFile = async (url: string, data: any) => {
    try {
      const response: AxiosResponse<FileUploadResponse> =
        await dbApiWithoutAuth.post(url, data, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });
      const statusCode = response.status;
      console.log("Response Status Code:", statusCode);
      return response.data;
    } catch (error) {
      const axiosError = error as AxiosError;
      if (axiosError.response) {
        const errorStatusCode = axiosError.response.status;
        console.log("Error Status Code:", errorStatusCode);

        throw axiosError.response.data;
      } else {
        throw axiosError.message;
      }
    }
  };

  async function handleRegistrationSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (regFormData.password !== regFormData.repeatPassword) {
      setError("Password and repeat password do not match");
      return;
    }
    try {
      const responseData = await register("/api/v1/auth/register", regFormData);
      console.log("Response: ", responseData);
      const token = responseData.accessToken;
      const refreshToken = responseData.refreshToken;
      console.log(token);
      if (token) {
        //localStorage.setItem("token", JSON.stringify(token));
        localStorage.setItem("token", token);
        localStorage.setItem("refresh_token", refreshToken);

        setUser(token);
      }
      const decoded = jwt_decode(token);
      console.log(decoded);

      if (file) {
        const uploadData = await sendFile("/uploadFile", file);
        console.log(uploadData);
      }

      setRegFormData({
        name: "",
        email: "",
        password: "",
        repeatPassword: "",
        role: { roleId: 0, roleName: "" },
        department: { departmentId: 0, departmentName: "" },
      });

      setFile(null);

      navigate("/");
    } catch (error) {
      console.error("Error:", error);
      setError("Registration failed. Please try again.");
    }
  }

  return (
    <main className="register-form">
      <div className="logo-container">
        <img src={logo} alt="ideas logo" className="logo" />
      </div>
      <form
        className="register-form__container"
        onSubmit={handleRegistrationSubmit}
        noValidate
        autoComplete="off"
      >
        <div className="register-form__left">
          <div className="register-form__field">
            <RegisterTextField
              type="text"
              name="name"
              label="Name"
              variant="standard"
              InputProps={{
                disableUnderline: true,
              }}
              required
              onChange={handleRegistrationInput}
            ></RegisterTextField>
          </div>
          <div className="register-form__field">
            <RegisterTextField
              type="email"
              name="email"
              label="E-mail"
              variant="standard"
              InputProps={{
                disableUnderline: true,
              }}
              required
              onChange={handleRegistrationInput}
            ></RegisterTextField>
          </div>
          <div className="register-form__field">
            <RegisterTextField
              type="password"
              name="password"
              label="Password"
              variant="standard"
              InputProps={{
                disableUnderline: true,
              }}
              required
              onChange={handleRegistrationInput}
            ></RegisterTextField>
          </div>
          <div className="register-form__field">
            <RegisterTextField
              type="password"
              name="repeatPassword"
              label="Repeat Password"
              variant="standard"
              InputProps={{
                disableUnderline: true,
              }}
              required
              onChange={handleRegistrationInput}
            ></RegisterTextField>
          </div>
        </div>

        <div className="register-form__right">
          <div className="register-form__select-container">
            <FormControl>
              <RegisterSelectLabel id="role-select-label">
                Role
              </RegisterSelectLabel>
              <RegisterSelect
                id="role"
                labelId="role-select-label"
                name="role"
                value={regFormData.role.roleId.toString()}
                required
                variant="standard"
                disableUnderline
                onChange={handleSelectChange}
              >
                {roles.map((role) => (
                  <RegisterSelectMenuItem
                    key={role.roleId}
                    value={role.roleId.toString()}
                  >
                    {role.roleName}
                  </RegisterSelectMenuItem>
                ))}
              </RegisterSelect>
            </FormControl>
          </div>
          <div className="register-form__select-container">
            <FormControl>
              <RegisterSelectLabel id="department-select-label">
                Department
              </RegisterSelectLabel>
              <RegisterSelect
                id="department"
                labelId="department-select-label"
                name="department"
                value={regFormData.department.departmentId.toString()}
                required
                variant="standard"
                disableUnderline
                onChange={handleSelectChange}
              >
                {departments.map((department) => (
                  <RegisterSelectMenuItem
                    key={department.departmentId}
                    value={department.departmentId.toString()}
                  >
                    {department.departmentName}
                  </RegisterSelectMenuItem>
                ))}
              </RegisterSelect>
            </FormControl>
          </div>
          <div className="register-form__buttons">
            <RegisterButton
              variant="contained"
              style={{ textTransform: "none" }}
              startIcon={<CloudUploadIcon />}
            >
              Upload your photo
              <input
                type="file"
                name="photo"
                accept="image/*"
                style={{ display: "none" }}
                onChange={handleFileChange}
              />
            </RegisterButton>
            {/* Display error message  */}
            {error && <div className="register-form__error">{error}</div>}

            <div className="register-form__register-button-and-nav-text">
              <RegisterButton
                variant="contained"
                style={{ textTransform: "none" }}
                type="submit"
              >
                Register account
              </RegisterButton>
              <LinkText href="/login">
                Already have an account? <SignInText>Sign-in</SignInText>
              </LinkText>
            </div>
          </div>
        </div>
      </form>
    </main>
  );
};
