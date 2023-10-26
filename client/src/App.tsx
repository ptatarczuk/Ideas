import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout/Layout";
import UsersList from "./pages/UsersList/UsersList";
import Login from "./pages/Login/Login";
import "./App.css";
import { Threads } from "./components/ThreadsList/Threads";
import { NotFound } from "./pages/NotFound/NotFound";
import { UnauthorizedRoute } from "./components/UnauthorizedRoute";
import { Registration } from "./pages/Registration/Registration";
import { ThreadPage } from "./pages/Thread/Thread";
import { ForgotPassword } from "./components/Password/ForgotPassword";
import { ResetPassword } from "./components/Password/ResetPassword";
import { ChangePassword } from "./components/Password/ChangePassword";
import AddThread from "./components/AddThread/AddThread";
import { ProtectedRoute } from "./components/ProtectRoute";

function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<Layout />}>
                  <Route
                      index
                      element={
                          <ProtectedRoute>
                              <Threads />
                          </ProtectedRoute>
                      }
                  />
                  <Route
                      path="users"
                      element={
                          <ProtectedRoute>
                              <UsersList />
                          </ProtectedRoute>
                      }
                  />
                  <Route
                      path="threads"
                      element={
                          <ProtectedRoute>
                              <Threads />
                          </ProtectedRoute>
                      }
                  />
                  <Route
                      path="add"
                      element={
                          <ProtectedRoute>
                              <AddThread />
                          </ProtectedRoute>
                      }
                  />
                  <Route
                      path="thread/:id"
                      element={
                          <ProtectedRoute>
                              <ThreadPage />
                          </ProtectedRoute>
                      }
                  />
                  <Route
                      path="*"
                      element={
                          <UnauthorizedRoute>
                              <NotFound />
                          </UnauthorizedRoute>
                      }
                  />

                  <Route
                      path="change-password"
                      element={
                          <UnauthorizedRoute>
                              <ChangePassword />
                          </UnauthorizedRoute>
                      }
                  />
              </Route>

              <Route
                  path="reset-password/:token"
                  element={
                      <UnauthorizedRoute>
                          <ResetPassword />
                      </UnauthorizedRoute>
                  }
              />

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

              <Route
                  path="/reset-password"
                  element={
                      <UnauthorizedRoute>
                          <ForgotPassword />
                      </UnauthorizedRoute>
                  }
              ></Route>
          </Routes>
      </BrowserRouter>
  );
}

export default App;
