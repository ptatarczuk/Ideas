import { Navigate } from "react-router-dom";
import React from "react";
import { ACCESS_TOKEN } from "./constants";

export const ProtectedRoute = ({ children }: React.PropsWithChildren) => {
  if (!localStorage.getItem(ACCESS_TOKEN)) {
    return <Navigate to={"/login"} replace />;
  }

  return <>{children}</>;
};