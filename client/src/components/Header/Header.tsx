import React from 'react';
import { Link, NavLink } from 'react-router-dom';

const Header: React.FC = () => {
    const activeStyles: React.CSSProperties = {
        fontWeight: "bold",
        textDecoration: "underline",
        color: "#161616"
    };

    return (
        <header>
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
                    to="/login"
                    style={({ isActive }) => (isActive ? activeStyles : null) as React.CSSProperties}
                >
                    LOG OUT
                </NavLink>

            </nav>
        </header>
    );
};

export default Header;
