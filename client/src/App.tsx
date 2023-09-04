<<<<<<< HEAD
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Layout from './components/Layout/Layout';
import UsersList from './pages/UsersList/UsersList';
import Login from './pages/Login/Login';
import './App.css';
import { Threads } from './components/Threads/Threads';
import { NotFound } from './components/NotFound/NotFound';
import { ThreadPage } from './components/Threads/ThreadPage';
=======
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout/Layout";
import UsersList from "./pages/UsersList/UsersList";
import Login from "./pages/Login/Login";
import "./App.css";
import { Threads } from "./components/Threads/Threads";
import { NotFound } from "./components/NotFound/NotFound";
import { UnauthorizedRoute } from "./components/UnauthorizedRoute";
import { Registration } from "./pages/Registration/Registration";

>>>>>>> main
function App() {
  return (
    // TODO : Dorobic Protected route !
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Threads />} />
          <Route path="users" element={<UsersList />} />
          <Route path="threads" element={<Threads />} />
          <Route path="*" element={<NotFound />} />
        </Route>

<<<<<<< HEAD
<Routes>
      <Route path="/" element={<Layout />} >
      <Route index element={<Threads />} />
      <Route path="users" element={<UsersList />} />
      <Route path="login" element={<Login />} />
      <Route path="threads" element={<Threads />} />
      <Route path="thread/:id" element={<ThreadPage />} />
      <Route path="*" element={<NotFound />} />
      </Route>
    </Routes>
  </BrowserRouter>
=======
        <Route
          path="/login"
          element={
            <UnauthorizedRoute>
              <Login />
            </UnauthorizedRoute>
          }
        ></Route>

        <Route
          path="/registration"
          element={
            <UnauthorizedRoute>
              <Registration />
            </UnauthorizedRoute>
          }
        ></Route>
      </Routes>
    </BrowserRouter>
>>>>>>> main
  );
}

export default App;
