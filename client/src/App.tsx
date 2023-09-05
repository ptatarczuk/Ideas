
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout/Layout";
import UsersList from "./pages/UsersList/UsersList";
import Login from "./pages/Login/Login";
import "./App.css";
import { Threads } from "./components/ThreadsList/Threads";
import { NotFound } from "./components/NotFound/NotFound";
import { UnauthorizedRoute } from "./components/UnauthorizedRoute";
import { Registration } from "./pages/Registration/Registration";
import {ThreadPage} from './pages/Thread/Thread'


function App() {
  return (
    // TODO : Dorobic Protected route !
    //TODO: ograniczyć dostęp do LOGIN dla ju zalogowanych uytkowników
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />} >
          <Route index element={<Threads />} />
          <Route path="users" element={<UsersList />} />
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
          <Route path="threads" element={<Threads />} />
          <Route path="thread/:id" element={<ThreadPage />} />
          <Route path="*" element={<NotFound />} />
        </Route>

        <Route
          path="/login"
          element={
            <UnauthorizedRoute>
              <Login />
            </UnauthorizedRoute>
          }
        ></Route>

        <Route
          path="/register"
          element={
            <UnauthorizedRoute>
              <Registration />
            </UnauthorizedRoute>
          }
        ></Route>
      </Routes>
    </BrowserRouter>

  );
}

export default App;
