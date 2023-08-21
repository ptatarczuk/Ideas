import { useState, useEffect, FormEvent } from "react";

interface LoginData {
    email: string;
    password: string;
}

export default function Login() {
    const [formData, setFormData] = useState<LoginData>({
        email: '',
        password: ''
    });
    

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
            const response = await fetch('http://localhost:8080/users/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });

            if (response.ok) {
                const data = await response.json();
                sessionStorage.setItem('token', data.token);
                setFailedAttempts(0);
                //navigate to home page
            } else {
                const errorData = await response.json();
                console.log('Error:', errorData);
                const newFailedAttempts: number = failedAttempts + 1;
                setFailedAttempts(newFailedAttempts);
                setBlockTime(Date.now() + 10000);
                alert(`Login or password is incorrect. Try again.`);
            }
        } catch (error: any) {
            console.log(`Error: ${error.message}`);
            //to tylko do testów
            // const newFailedAttempts: number = failedAttempts + 1;
            //     setFailedAttempts(newFailedAttempts);
            //     setBlockTime(Date.now() + 10000); 
        }
        //navigate
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
