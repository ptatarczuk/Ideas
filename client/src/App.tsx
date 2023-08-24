import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Layout from './components/Layout/Layout';
import UsersList from './pages/UsersList/UsersList';
import Ideas from './pages/Ideas/Ideas';
import Login from './pages/Login/Login';
import './App.css';

function App() {

  return (
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<Layout />} >
      <Route index element={<Ideas />} />
      <Route path="users" element={<UsersList />} />
      <Route path="login" element={<Login />} />
      </Route>
    </Routes>
  </BrowserRouter>
  );
}

export default App;
