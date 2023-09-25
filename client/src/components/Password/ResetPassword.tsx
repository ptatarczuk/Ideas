import { Box, Button, TextField } from "@mui/material";
import { ChangeEvent, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import logo from "../../assets/logo.svg";

interface ResetPasswordComponentProps {}

export const ResetPassword: React.FC<ResetPasswordComponentProps> = ({}) => {
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");
  const [showPassword, setShowPassword] = useState<boolean>(false);
  const { token } = useParams();

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const handleSubmit = (event: React.MouseEvent<HTMLButtonElement>) => {
    if (password === confirmPassword) {
      handlePasswordChange();
    }
  };

  useEffect(() => {
    console.log("Token:", token);
  }, [token]);

  const handlePasswordChange = async () => {
    try {
      const dataToSend = {
        password: password, 
      };
      const response = await fetch(
        `http://localhost:8080/api/v1/auth/reset-password/${token}`,
        {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(dataToSend),
        }
      );

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
    } catch (error) {
      console.error("An error occurred while saving data", error);
    }
  };

  return (
    <>
      <div className="logo-container">
        <img src={logo} alt="ideas logo" className="logo" />
      </div>
      <Box
        className="form_container"
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column", 
          alignItems: "center",
          justifyContent: "top", 
          height: "70vh",
        }}
        noValidate
        autoComplete="off"
      >
        <TextField
          required
          id="outlined-required"
          label="Password"
          variant="filled"
          type={showPassword ? "text" : "password"}
          value={password}
          InputProps={{
            disableUnderline: true,
          }}
          onChange={(event: ChangeEvent<HTMLInputElement>) =>
            setPassword(event.target.value)
          }
          sx={{ marginTop: "50px", background: "white", borderRadius: "15px" }}
        />
        <TextField
          required
          id="outlined-required"
          label="Confirm Password"
          variant="filled"
          type={showPassword ? "text" : "password"}
          value={confirmPassword}
          InputProps={{
            disableUnderline: true,
          }}
          onChange={(event: ChangeEvent<HTMLInputElement>) =>
            setConfirmPassword(event.target.value)
          }
          sx={{ marginTop: "25px", background: "white", borderRadius: "15px" }}
        />
        <Button
          onClick={togglePasswordVisibility} 
          variant="contained"
          style={{
            marginTop: "25px",
            backgroundColor: "black",
            color: "#FF8900",
            borderRadius: "15px",
          }}
        >
          {!showPassword ? "Show password" : "Hide Password"}
        </Button>
        <Button
          onClick={handleSubmit}
          variant="contained"
          style={{
            marginTop: "25px",
            backgroundColor: "black",
            color: "#FF8900",
            borderRadius: "15px",
          }}
        >
          Confirm Change
        </Button>
      </Box>
    </>
  );
};
