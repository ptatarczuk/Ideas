import { Box, Button, TextField } from "@mui/material";
import { ChangeEvent, useEffect, useState } from "react";
import { useParams } from "react-router-dom";

interface ResetPasswordComponentProps {

}

export const ResetPassword: React.FC<ResetPasswordComponentProps> = ({}) => {
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");
   const { token } = useParams();
  // const [tokenValue, setTokenValue] = useState(token);




  const handleSubmit = (event: React.MouseEvent<HTMLButtonElement>) => {
    
    if (password === confirmPassword) {
      handlePasswordChange();
    }

  }

  useEffect(() =>  {
    console.log("Token:", token);

  }, [token])

  const handlePasswordChange = async () => {
    try {
    //   const dataToSend = {
    //     password
    // }; 
        const response = await fetch(`http://localhost:8080/api/v1/auth/reset-password/${token}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(password),
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
    label="Password"
    value={password}
    onChange={(event: ChangeEvent<HTMLInputElement>) => setPassword(event.target.value)}
  />
    <TextField
    required
    id="outlined-required"
    label="Confirm Password"
    value={confirmPassword}
    onChange={(event: ChangeEvent<HTMLInputElement>) => setConfirmPassword(event.target.value)}
  />
  <Button onClick={handleSubmit} >
    Confirm Password
  </Button>
</Box>
  );
};