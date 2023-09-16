import { Input, TextField, Box, Button } from "@mui/material";
import { ChangeEvent, useState } from "react";
import { Form, useSubmit } from "react-router-dom";
import { BrowserRouter as Router } from "react-router-dom";

interface ForgotPasswordComponentProps {}

export const ForgotPassword: React.FC<ForgotPasswordComponentProps> = ({}) => {
  const [inputValue, setInputValue] = useState<String>("");

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
        const response = await fetch(`http://localhost:8080/api/v1/auth/reset-password?email=${inputValue}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            //body: JSON.stringify(dataToSend),
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
        value={inputValue}
        onChange={(e: ChangeEvent<HTMLInputElement>) => setInputValue(e.target.value)}
      />
      <Button onClick={handleSubmit} >
        Reset Password
      </Button>
    </Box>
  );
};