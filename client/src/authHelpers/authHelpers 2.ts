import axios from "axios";
import jwtDecode from "jwt-decode";

export const getJwtToken = async () => {
    const jwtToken = localStorage.getItem("token");


    if (verifyIfTokenIsValid(jwtToken)) {
        return jwtToken;
    }
    const jwtRefreshToken = localStorage.getItem("refresh_token");

    if (!verifyIfTokenIsValid(jwtRefreshToken)) {
        handleLogout();
        return null;
    }

    try {
        const response = await fetchNewTokens(jwtRefreshToken!);
        if (response.status === 200) {
            saveTokensToLocaleStorage(response.data);
            return jwtToken;
        }
    } catch (error: any) {
        console.log("Błąd podczas odświeżania tokena:", error.message);
    }
}


async function fetchNewTokens(jwtRefreshToken: string) {
    return await axios.post("http://localhost:8080/api/v1/auth/refresh-token", null, {
        headers: {
            Authorization: `Bearer ${jwtRefreshToken}`,
        },
    });
}

function isTokenExpired(tokenExp: number, currentTimestamp: number) {
    return tokenExp < currentTimestamp;
}


export function saveTokensToLocaleStorage({ access_token, refresh_token }: { access_token: string, refresh_token: string }) {
    localStorage.setItem("token", access_token);
    localStorage.setItem("refresh_token", refresh_token);
}

function verifyIfTokenIsValid(jwtToken: string | null) {
    const currentTimestamp = Math.round(Date.now() / 1000);
    if (jwtToken === null || jwtToken.length === 0) {
        return false;
    }
    const decodedToken = jwtDecode(jwtToken) as any;
    const tokenExp = decodedToken.exp as number;

    return !isTokenExpired(tokenExp, currentTimestamp);
}

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
    // userContext.setUser(null);
};

