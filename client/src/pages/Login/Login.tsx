import { useState, useEffect, FormEvent, useContext } from "react";
import { UserContext } from "../../context/UserContext";
import { useNavigate } from "react-router-dom";
import logo from "../../assets/logo.svg";
import "./Login.css";
import { styled } from "@mui/system";
import { Button, TextField } from "@mui/material";

interface LoginData {
  email: string;
  password: string;
}

const LoginTextField = styled(TextField)`
  background-color: #ffffff;
  border: none;
  border-radius: 69px;
  border-color: #ffffff;
  opacity: 1;
  width: 406.75px;
  height: 49.25px;
  margin-left: 30px;
  margin-top: 10px;

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

const LoginButton = styled(Button)`
  &&.MuiButton-root {
    background-color: #000000 !important;
    text-transform: none;
    border-radius: 69px !important;
    width: 201px;
    height: 56.75px;
    margin-right: 1.5%;
    margin-bottom: 20px;
    margin-top: 10px;
    margin-left: 3.5%;
    font-family: "Poppins Light", sans-serif;
    border: none;
    outline: none;
  }

  &&:hover {
    background-color: #ffffff !important;
    color: #000000 !important;
  }

  &&.MuiButtonBase-root {
    background-color: #000000;
    color: #ff8900;
    text-transform: none;
    border-radius: 10px;
    font-family: "Poppins Light", sans-serif;
  }

  &&.MuiButton-root {
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

const SignupInText = styled("span")`
  color: #000000;
  font-family: "Poppins Bold", sans-serif;
`;

const Login: React.FC = () => {
  const [formData, setFormData] = useState<LoginData>({
    email: "",
    password: "",
  });

  const { setUser } = useContext(UserContext);
  const navigate = useNavigate();

  const [failedAttempts, setFailedAttempts] = useState(0);
  const [blockTime, setBlockTime] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      if (blockTime > Date.now()) {
        const remainingTime = Math.ceil((blockTime - Date.now()) / 1000);
        alert(`Account is blocked. Try again after ${remainingTime} seconds.`);
      }
    }, 1000);

    return () => {
      clearInterval(interval);
    };
  }, [blockTime]);

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    try {
      const response = await fetch(
        "http://localhost:8080/api/v1/auth/authenticate",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        }
      );

      if (response.ok) {
        console.log("Login successful!");
        const data: { access_token: string } = await response.json();
        console.log(data);
        localStorage.setItem("token", JSON.stringify(data.access_token));
        setUser(data.access_token); //lub nazwa uzytkownika?
        setFailedAttempts(0);
        navigate("/");
      } else {
        console.log("Login failed!");
        const errorData: { message: string } = await response.json();
        console.log("Error:", errorData);
        const newFailedAttempts: number = failedAttempts + 1;
        setFailedAttempts(newFailedAttempts);
        setBlockTime(Date.now() + 10000);
        alert(`Login or password is incorrect. Try again.`);
      }
    } catch (error: any) {
      console.log(`Error: ${error.message}`);
    }
    setFormData({ email: "", password: "" });
  }

  return (
    <main className="login-form">
      <div className="logo-container">
        <img src={logo} alt="ideas logo" className="logo" />
      </div>
      <form
        className="login-form__container"
        onSubmit={handleSubmit}
        noValidate
        autoComplete="off"
      >
        {" "}
        <div className="login-form__container-fields">
          <div className="login-form__email-container">
            <LoginTextField
              className="login-form__input"
              type="email"
              label="E-mail"
              variant="standard"
              InputProps={{
                disableUnderline: true,
              }}
              required
              onChange={(e) =>
                setFormData({ ...formData, email: e.target.value })
              }
              value={formData.email}
            />
          </div>
          <div className="login-form__password-container">
            <LoginTextField
              className="login-form__input"
              type="password"
              label="Password"
              variant="standard"
              InputProps={{
                disableUnderline: true,
              }}
              required
              onChange={(e) =>
                setFormData({ ...formData, password: e.target.value })
              }
              value={formData.password}
            />
          </div>
          <div className="login-form__show-password">
            <button
              type="button"
              className="login-form-show-password-button"
            ></button>
            <div className="login-form-show-password-text">Show password</div>
          </div>
          <LoginButton
            className="login-form__button"
            type="submit"
            variant="text"
            style={{ textTransform: "none" }}
          >
            Sign in
          </LoginButton>
          <div className="login-form__links-forgot-password">
            <LinkText href="#">Forgot Username/Password</LinkText>
          </div>
          <div className="login-form__links-register">
            <LinkText href="/register">
              Don't have an account? <SignupInText>Signup</SignupInText>
            </LinkText>
          </div>
        </div>
      </form>
    </main>
  );
};

export default Login;
