
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout/Layout";
import UsersList from "./pages/UsersList/UsersList";
import Login from "./pages/Login/Login";
import "./App.css";
import { Threads } from "./components/ThreadsList/Threads";
import { NotFound } from "./pages/NotFound/NotFound";
import { UnauthorizedRoute } from "./components/UnauthorizedRoute";
import { Registration } from "./pages/Registration/Registration";
import {ThreadPage} from './pages/Thread/Thread'
import { ForgotPassword } from "./components/Password/ForgotPassword";
import { ResetPassword } from "./components/Password/ResetPassword";
import { ChangePassword } from "./components/Password/ChangePassword";
import AddThread from "./components/AddThread/AddThread";


function App() {
  return (
    // TODO : Dorobic Protected route !
    //TODO: ograniczyć dostęp do LOGIN dla ju zalogowanych uytkowników
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />} >
          <Route index element={<Threads />} />
          <Route path="users" element={<UsersList />} />
          {/* <Route
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
          ></Route> */}
          <Route path="threads" element={<Threads />} />
          <Route path="add_thread" element={<AddThread />} />
          <Route path="thread/:id" element={<ThreadPage />} />
          <Route path="*" element={<NotFound />} />
          <Route path="reset-password" element={<ForgotPassword />} />
          <Route path="reset-password/:token" element={<ResetPassword />} />
          <Route path="change-password" element={<ChangePassword />} />
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
