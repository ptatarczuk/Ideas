import { Navigate } from "react-router-dom";
import React from "react";
 import { ACCESS_TOKEN } from "./constants";   

 export const UnauthorizedRoute = ({ children }: React.PropsWithChildren) =>
 localStorage.getItem(ACCESS_TOKEN) ? ( <Navigate to={"/profile"} replace /> ) : ( <>{children}</>
 );