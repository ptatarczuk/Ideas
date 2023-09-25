import { Input, TextField, Box, Button, Typography } from "@mui/material";
import { ChangeEvent, useState } from "react";
import { Form, useSubmit } from "react-router-dom";
import { BrowserRouter as Router } from "react-router-dom";
import logo from "../../assets/logo.svg";
import "./ForgotPassword.css";

interface ForgotPasswordComponentProps {}

export const ForgotPassword: React.FC<ForgotPasswordComponentProps> = ({}) => {
  const [inputValue, setInputValue] = useState<String>("");
  const [isSent, setIsSent] = useState<boolean>(true);

  const handleSubmit = (event: React.MouseEvent<HTMLButtonElement>) => {
    //event.preventDefault();
    handleSend();

    // przejscie na nowy adres
  };

  const handleSend = async () => {
    try {
      /* const dataToSend = {
            email: inputValue
        }; */

      //console.log(dataToSend);
      const response = await fetch(
        `http://localhost:8080/api/v1/auth/reset-password?email=${inputValue}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          //body: JSON.stringify(dataToSend),
        }
      );

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      setIsSent(true);
    } catch (error) {
      console.error("An error occurred while saving data", error);
    }
  };

  return (
    <main className="register-form">
      <div className="logo-container">
        <img src={logo} alt="ideas logo" className="logo" />
      </div>

      <Box
        className="form_container"
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "top",
          height: "70vh",
        }}
      >
        {!isSent ? (
          <>
        <TextField
          className="form_field"
          required
          id="outlined-required"
          label="Email"
          variant="filled"
          value={inputValue}
          InputProps={{
            disableUnderline: true,
          }}
          onChange={(event: ChangeEvent<HTMLInputElement>) =>
            setInputValue(event.target.value)
          }
          style={{ marginTop: "50px", backgroundColor: "white", borderRadius: "15px" }}
        />
        <Button
          onClick={handleSubmit}
          variant="contained"
          style={{
            marginTop: "20px",
            backgroundColor: "black",
            color: "#FF8900",
            borderRadius: "15px"
          }}
        >
          Reset Password
        </Button>
        </>
        ) : (
          <Typography variant="h6" style={{ marginTop: "50px", color: "white", fontSize: "30px" }}>
          Reset password request has been sent.
        </Typography>
        )}
      </Box>
    </main>
  );
};
