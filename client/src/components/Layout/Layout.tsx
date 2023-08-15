import React from 'react';
import { Outlet } from 'react-router-dom';
import Header from '../Header/Header';

const Layout: React.FC = () => {
    return (
        <div className="site-wrapper">
            <Header />
            <main>
                <Outlet />
            </main>
        </div>
    );
};

export default Layout;
