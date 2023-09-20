import { Box, Button, TextField } from "@mui/material";
import { ChangeEvent, useState } from "react";
import "./Password.css";

interface ChangePasswordComponentProps {}

export const ChangePassword: React.FC<ChangePasswordComponentProps> = ({}) => {
  const [email, setEmail] = useState<String>("");
  const [oldPassword, setOldPassword] = useState<String>("");
  const [newPassword, setNewPassword] = useState<String>("");
  const [confirmNewPassword, setConfirmNewPassword] = useState<String>("");
  const [showPassword, setShowPassword] = useState<boolean>(false);

  const handleSubmit = (event: React.MouseEvent<HTMLButtonElement>) => {
    if (newPassword === confirmNewPassword) {
      handleSend();
    }
    //event.preventDefault();

    // przejscie na nowy adres
  };

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const handleSend = async () => {
    try {
      const dataToSend = {
        email: email,
        password: oldPassword,
        newPassword: newPassword,
      };

      console.log(dataToSend);
      const response = await fetch(
        `http://localhost:8080/api/v1/auth/change-password`,
        {
          method: "POST",
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
    <Box
      className="form"
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
        label="Email"
        value={email}
        // InputProps={{
        //   disableUnderline: true,
        // }}
        onChange={(e: ChangeEvent<HTMLInputElement>) =>
          setEmail(e.target.value)
        }
        sx={{ marginTop: "50px", background: "white", borderRadius: "20px" }}
      />
      <TextField
        required
        id="outlined-required"
        label="Current Password"
        type={showPassword ? "text" : "password"}
        // InputProps={{
        //   disableUnderline: true,
        // }}
        value={oldPassword}
        onChange={(e: ChangeEvent<HTMLInputElement>) =>
          setOldPassword(e.target.value)
        }
        sx={{ marginTop: "15px", background: "white", borderRadius: "20px" }}
      />
      <TextField
        required
        id="outlined-required"
        label="New Password"
        type={showPassword ? "text" : "password"}
        // InputProps={{
        //   disableUnderline: true,
        // }}
        value={newPassword}
        onChange={(e: ChangeEvent<HTMLInputElement>) =>
          setNewPassword(e.target.value)
        }
        sx={{ marginTop: "15px", background: "white", borderRadius: "20px" }}
      />
      <TextField
        required
        id="outlined-required"
        label="Confirm New Password"
        type={showPassword ? "text" : "password"}
        // InputProps={{
        //   disableUnderline: true,
        // }}
        value={confirmNewPassword}
        onChange={(e: ChangeEvent<HTMLInputElement>) =>
          setConfirmNewPassword(e.target.value)
        }
        sx={{ marginTop: "15px", background: "white", borderRadius: "20px" }}
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
          backgroundColor: "#FF8900",
          color: "black",
          borderRadius: "15px",
        }}
      >
        Confirm Change
      </Button>
    </Box>
  );
};
