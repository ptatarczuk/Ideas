import { useState, useEffect, FormEvent, useContext } from "react";
import { UserContext } from "../../context/UserContext";
import { useNavigate } from "react-router-dom";



interface LoginData {
    email: string;
    password: string;
}

const Login: React.FC = () => {
    const [formData, setFormData] = useState<LoginData>({
        email: '',
        password: ''
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
            const response = await fetch('http://localhost:8080/api/v1/auth/authenticate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });

            if (response.ok) {
                console.log("Login successful!");
                const data: { access_token: string } = await response.json();
                console.log(data);
                sessionStorage.setItem("token", JSON.stringify(data.access_token));
                setUser(data.access_token); //lub nazwa uzytkownika?
                setFailedAttempts(0);
                navigate("/");

            } else {
                console.log("Login failed!");
                const errorData: { message: string } = await response.json();
                console.log('Error:', errorData);
                const newFailedAttempts: number = failedAttempts + 1;
                setFailedAttempts(newFailedAttempts);
                setBlockTime(Date.now() + 10000);
                alert(`Login or password is incorrect. Try again.`);
            }
        } catch (error: any) {
            console.log(`Error: ${error.message}`);
        }
        setFormData({ email: '', password: '' });

    }

    return (
        <main className="login-form">
            <form className="login-form__container" onSubmit={handleSubmit}>
                <input
                    className="login-form__input"
                    type="email"
                    placeholder="Email"
                    onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                    value={formData.email}
                />
                <input
                    className="login-form__input"
                    type="password"
                    placeholder="Password"
                    onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                    value={formData.password}
                />
                <button
                    className="login-form__button"
                    type="submit"
                >
                    Login
                </button>
            </form>
            <div className="login-form__links">
                <a href="#">Forgot Username/Password</a>
                <span> | </span>
                <a href="#">Don't have an account? Signup</a>
            </div>
        </main>
    );
}

export default Login;

