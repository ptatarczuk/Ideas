import React, {useState, FormEvent, ChangeEvent, useEffect} from 'react';
import { useNavigate } from "react-router-dom";


type RegistrationData = { 
    userName: string,
    email: string,
    password: string,
    repeatPassword: string
}


export const Registration: React.FC = () => {
    const [regFormData, setRegFormData] = useState<RegistrationData>(
    {
        userName: '',
        email: '',
        password: '',
        repeatPassword: ''
    })
    const navigate = useNavigate();
    

    function handleRegistrationInput(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target;
        setRegFormData({...regFormData, [name]: value})
    }

    async function handleRegistrationSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        console.log("Helloł")
        navigate("/login");
  
    }
    
    useEffect(() => {
        console.log(regFormData);
      }, [regFormData]);
    
    return (
        <main className="registration-form">
            <form className="registration-form__container" onSubmit={handleRegistrationSubmit}>
                <label htmlFor="userName" className="registration-form__label">User name:</label>
                <input type="text" name="userName" placeholder="User name" className="registration-form__input" onChange={handleRegistrationInput}></input>
                <label htmlFor="email" className="registration-form__label">Email:</label>
                <input type="email" name="email" placeholder="E-mail" className="registration-form__input" onChange={handleRegistrationInput}></input>
                <label htmlFor="password" className="registration-form__label">Password:</label>
                <input type="password" name="password" placeholder="Password" className="registration-form__input" onChange={handleRegistrationInput}></input>
                <label htmlFor="repeat-password" className="registration-form__label">Repeat password:</label>
                <input type="password" name="repeatPassword" placeholder="Repeat Password" className="registration-form__input" onChange={handleRegistrationInput}></input>
                <button className="registration-form__button" type="submit">Register account</button>
            </form>
            <div className="register-form__links">
                <a href="/login">Already have an account? Sign-in</a>
            </div>
        </main>
    );
}