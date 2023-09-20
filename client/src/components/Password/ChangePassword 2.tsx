import { Box, Button, TextField } from "@mui/material";
import { ChangeEvent, useState } from "react";

interface ChangePasswordComponentProps {}

export const ChangePassword: React.FC<ChangePasswordComponentProps> = ({}) => {
  const [email, setEmail] = useState<String>("");
  const [oldPassword, setOldPassword] = useState<String>("");
  const [newPassword, setNewPassword] = useState<String>("");
  const [confirmNewPassword, setConfirmNewPassword] = useState<String>("");
  

  const handleSubmit = (event: React.MouseEvent<HTMLButtonElement>) => {
    if (newPassword === confirmNewPassword)  {
      
      handleSend();
    }
    //event.preventDefault();
 

    // przejscie na nowy adres

  };

  const handleSend = async () => {
    try {
         const dataToSend = {
            email: email,
            password: oldPassword,
            newPassword: newPassword
        }; 

        console.log(dataToSend);   
        const response = await fetch(`http://localhost:8080/api/v1/auth/change-password`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dataToSend),
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
    } catch (error) {
        console.error('An error occurred while saving data', error);
    }
};




 return (

    <Box
      component="form"
      sx={{
        "& .MuiTextField-root": { m: 1, width: "25ch" },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField
        required
        id="outlined-required"
        label="Email"
        value={email}
        onChange={(e: ChangeEvent<HTMLInputElement>) => setEmail(e.target.value)}
      />
            <TextField
        required
        id="outlined-required"
        label="Current Password"
        value={oldPassword}
        onChange={(e: ChangeEvent<HTMLInputElement>) => setOldPassword(e.target.value)}
      />
            <TextField
        required
        id="outlined-required"
        label="New Password"
        value={newPassword}
        onChange={(e: ChangeEvent<HTMLInputElement>) => setNewPassword(e.target.value)}
      />
            <TextField
        required
        id="outlined-required"
        label="Confirm New Password"
        value={confirmNewPassword}
        onChange={(e: ChangeEvent<HTMLInputElement>) => setConfirmNewPassword(e.target.value)}
      />
      <Button onClick={handleSubmit} >
        Change password
      </Button>
    </Box>
  );
};