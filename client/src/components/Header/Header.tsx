    import React, {useContext} from 'react';
    import { Link, NavLink } from 'react-router-dom';
    import { UserContext } from '../../context/UserContext';

    const Header: React.FC = () => {
        const userContext = useContext(UserContext);

        const activeStyles: React.CSSProperties = {
            fontWeight: "bold",
            textDecoration: "underline",
            color: "#161616"
        };

        const handleLogout = async () => {

            try {
                const response = await fetch('http://localhost:8080/api/v1/auth/logout', {
                    method: 'POST',
                    headers: {  
                        'Authorization': `Bearer ${sessionStorage.getItem('token')}`
                    }
                });

                if (response.ok) {
                    console.log("User logged out on the server.");
                } else {
                    console.log("Failed to log out user on the server.");
                }
            } catch (error) {
                console.log("An error occurred while logging out on the server:", error);
            }

            sessionStorage.removeItem("token");
            userContext.setUser(null);
        }


        return (
            <header>
                <h1>{userContext.user}</h1>
                <Link className="site-logo" to="/">IDEAS LOGO</Link>
                <nav>
                    <NavLink
                        to="/users"
                        style={({ isActive }) => (isActive ? activeStyles : null) as React.CSSProperties}
                    >
                        User List
                    </NavLink>
                    <NavLink
                        to="/"
                        style={({ isActive }) => (isActive ? activeStyles : null) as React.CSSProperties}
                    >
                        Ideas
                    </NavLink>
                    <NavLink
                        to={userContext.user ? "/" : "/login"} 
                        style={({ isActive }) => (isActive ? activeStyles : null) as React.CSSProperties}
                        onClick={() => {
                            if (userContext.user) {
                                handleLogout();
                            }
                        }}

                    >
                        <button>{userContext.user ? "LOG OUT" : "LOG IN"}</button>
                    </NavLink>

                </nav>
            </header>
        );
    };

    export default Header;
