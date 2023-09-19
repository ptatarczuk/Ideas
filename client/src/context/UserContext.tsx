import { createContext, useState, useEffect } from "react";


type AuthUser = {
    email: string,
    password: string
}


export type UserContextType = {
    user: any;
    setUser: any;
}

type UserContextProviderType = {
    children: React.ReactNode;
}

export const UserContext = createContext({} as UserContextType);


export const UserContextProvider: React.FC<UserContextProviderType> = ({ children }) => {
    const [user, setUser] = useState<AuthUser | null>(() => {
      const storedUser = localStorage.getItem("user");
      return storedUser ? JSON.parse(storedUser) : null;
    });
  
    useEffect(() => {
      console.log(user);
      if (user) {
        localStorage.setItem("user", JSON.stringify(user));
      } else {
        localStorage.removeItem("user");
      }
    }, [user]);
  
    return <UserContext.Provider value={{ user, setUser }}>{children}</UserContext.Provider>;
  };