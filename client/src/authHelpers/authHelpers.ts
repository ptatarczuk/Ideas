import axios from "axios";
import jwtDecode from "jwt-decode";
import { UserContext } from "../context/UserContext";
import useContext  from "react";



export const getJwtToken = async () => {

    try {
        const jwtToken = localStorage.getItem("token");


        if (jwtToken === null || jwtToken.length === 0) {
            return null
        }

        const decodedToken = jwtDecode(jwtToken) as any;

        const tokenExp = decodedToken.exp as number;

        const currentTimestamp = Math.round(Date.now() / 1000);

        if (tokenExp > currentTimestamp) {
            return jwtToken;
        }

        //pobieramy refresh

        const jwtRefreshToken = localStorage.getItem("refresh_token");

        if (jwtRefreshToken === null || jwtRefreshToken.length === 0) {
            return null // czy tu jeszcze wylogować
        }

        const decodedRefreshToken = jwtDecode(jwtRefreshToken) as any;

        const refreshTokenExp = decodedRefreshToken.exp as number;

        //sprawdzamy wazność refresh

        if (!(refreshTokenExp > currentTimestamp)) {
            //wylogowujemy uytkownika, usuwamy z localstorage
        }

        //jeśli wazny to zapytanie do back -> POST /refreshToken -> dostajemy nowy token i refresh
            //zapisać do localStorage 
            // zwracamy nowy jwtToken

        //zapisać do localStorage 

        // const token = await RequestHelper.handleAnyRequest(() =>
        //     AuthService.getJwtToken()
        // );

        // if (!token) {
        //     return null;
        // }

        // localStorage.setItem(jwtLocalStorageKey, token);

        // return token;
    } catch (error) {
        return null;
    }
};


export const handleLogout = async () => {
    try {
      const response = await axios.post(
        'http://localhost:8080/api/v1/auth/logout',
        {},
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`,
          },
        }
      );
  
      if (response.status === 200) {
        console.log('User logged out on the server.');
      } else {
        console.log('Failed to log out user on the server.');
      }
    } catch (error) {
      console.log('An error occurred while logging out on the server:', error);
    }
  
    localStorage.clear();
    sessionStorage.removeItem('token');
    // userContext.setUser(null);
  };