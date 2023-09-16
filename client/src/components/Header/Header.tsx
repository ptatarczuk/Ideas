import React, { useContext } from "react";
import { UserContext } from "../../context/UserContext";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import Container from "@mui/material/Container";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import AdbIcon from "@mui/icons-material/Lightbulb";
import { Link } from "react-router-dom";
import axios from "axios";
import { styled } from "@mui/system";
import logo from "../../assets/logo.svg";
import "./Header.css";
import jwt_decode from "jwt-decode";

type DecodedToken = {
  name: string;
};

const IdeaAppBar = styled(AppBar)`
  background-color: #000000;
  display: flex;
  justify-content: space-between;
`;

const NavBox = styled(Box)`
  display: flex;
  justify-content: flex-end;
  a:hover {
    color: #FF8900 !important;
  }
`;

const NavButton = styled(Button)`
  font-family: "Poppins Light", sans-serif;
`;

const HelloText = styled(Typography)`
  font-family: "Poppins Light", sans-serif;
  margin-right: 2%;
  padding: 2%;
  font-size: 14px;
`;

const LogoutButton = styled(Button)`
  background-color: #FF8900;
  border-radius: 38px;
  width:  136px;
  height: 32px;
  font-family: "Poppins Light", sans-serif;
  margin-left: 3%;
  margin-top: 2%;

  &:hover {
    background-color: #ffffff !important;
    color: #000000 !important;
  }
`;

const pages = ["Threads", "Users"]; // TODO: przerobic na enum -> poki co nie wiem jak zrobic mapowanie przez ten enum
const settings = ["Profile", "Account", "Dashboard", "Logout"];

function ResponsiveAppBar() {
  const userContext = useContext(UserContext);
  const jwtToken = localStorage.getItem("token");

  const handleLogout = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/auth/logout",
        {},
        {
          headers: {
            // TODO: Dodac interceptory zebysmy  nie musieli w kazdym zapytaniu wpisywac headerow
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );

      if (response.status === 200) {
        console.log("User logged out on the server.");
      } else {
        console.log("Failed to log out user on the server.");
      }
    } catch (error) {
      console.log("An error occurred while logging out on the server:", error);
    }

    sessionStorage.removeItem("token");
    userContext.setUser(null);
  };

  const displayNameFromToken = (jwtToken: string): string | null => {
    try {
      const decoded: DecodedToken = jwt_decode(jwtToken);
      return decoded.name;
    } catch (error) {
      console.error("Error decoding JWT token:", error);
      return null;
    }
  };

  /*   const handleLogout = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/v1/auth/logout", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
      });

      if (response.ok) {
        console.log("User logged out on the server.");
      } else {
        console.log("Failed to log out user on the server.");
      }
    } catch (error) {
      console.log("An error occurred while logging out on the server:", error);
    }
    sessionStorage.removeItem("token");
    userContext.setUser(null);
  }; */

  const [anchorElNav, setAnchorElNav] = React.useState<null | HTMLElement>(
    null
  );
  const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(
    null
  );

  const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  return (
    <IdeaAppBar position="static">
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <div className="navbar-left">
            <nav>
              <Link to="/">
                <img src={logo} alt="ideas logo" className="navbar-logo" />
              </Link>
              {/* Other navbar content */}
            </nav>
          </div>
          <div className="navbar-center">
            <NavBox
              sx={{
                flexGrow: 1,

                display: { xs: "flex", md: "none" },

                // display: "flex",
                // justifyContent: "center", // Center horizontally
                // alignItems: "center", // Center vertically
              }}
            >
              {/* <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton> */}
              <Menu
                id="menu-appbar"
                anchorEl={anchorElNav}
                anchorOrigin={{
                  vertical: "bottom",
                  horizontal: "left",
                }}
                keepMounted
                transformOrigin={{
                  vertical: "top",
                  horizontal: "left",
                }}
                open={Boolean(anchorElNav)}
                onClose={handleCloseNavMenu}
                sx={{
                  display: { xs: "block", md: "none" },
                }}
              >
                {pages.map((page) => (
                  <MenuItem key={page} onClick={handleCloseNavMenu}>
                    <Typography textAlign="center">
                      <Link
                        style={{ textDecoration: "none", color: "white" }}
                        to={`/${page}`}
                      >
                        {page}
                      </Link>
                    </Typography>
                  </MenuItem>
                ))}
              </Menu>
            </NavBox>
            <AdbIcon sx={{ display: { xs: "flex", md: "none" }, mr: 1 }} />

            <NavBox sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
              {pages.map((page) => (
                <NavButton
                  key={page}
                  onClick={handleCloseNavMenu}
                  sx={{ my: 2, color: "white", display: "block" }}
                >
                  <Link
                    style={{ textDecoration: "none", color: "white" }}
                    to={`/${page}`}
                  >
                    {page}
                  </Link>
                </NavButton>
              ))}
            </NavBox>
          </div>
          <div className="navbar-right">
            <NavBox sx={{ flexGrow: 0 }}>
              <HelloText>
                {jwtToken ? "Welcome, " + displayNameFromToken(jwtToken) : ""}
              </HelloText>
              {jwtToken ? <Link to="/logout" style={{ textDecoration: "none" }}>
                <LogoutButton variant="contained">Log out</LogoutButton>
              </Link> : ""}
            </NavBox>
          </div>
        </Toolbar>
      </Container>
    </IdeaAppBar>
  );
}
export default ResponsiveAppBar;

// const Header: React.FC = () => {
//     const userContext = useContext(UserContext);

//     const activeStyles: React.CSSProperties = {
//         fontWeight: "bold",
//         textDecoration: "underline",
//         color: "#161616"
//     };

//     const handleLogout = async () => {

//         try {
//             const response = await fetch('http://localhost:8080/api/v1/auth/logout', {
//                 method: 'POST',
//                 headers: {
//                     'Authorization': `Bearer ${sessionStorage.getItem('token')}`
//                 }
//             });

//             if (response.ok) {
//                 console.log("User logged out on the server.");
//             } else {
//                 console.log("Failed to log out user on the server.");
//             }
//         } catch (error) {
//             console.log("An error occurred while logging out on the server:", error);
//         }

//         sessionStorage.removeItem("token");
//         userContext.setUser(null);
//     }

//     return (
//         <header>
//             <h1>{userContext.user}</h1>
//             <Link className="site-logo" to="/">IDEAS LOGO</Link>
//             <nav>
//                 <NavLink
//                     to="/users"
//                     style={({ isActive }) => (isActive ? activeStyles : null) as React.CSSProperties}
//                 >
//                     User List
//                 </NavLink>
//                 <NavLink
//                     to="/"
//                     style={({ isActive }) => (isActive ? activeStyles : null) as React.CSSProperties}
//                 >
//                     Ideas
//                 </NavLink>
//                 <NavLink
//                     to={userContext.user ? "/" : "/login"}
//                     style={({ isActive }) => (isActive ? activeStyles : null) as React.CSSProperties}
//                     onClick={() => {
//                         if (userContext.user) {
//                             handleLogout();
//                         }
//                     }}

//                 >
//                     <button>{userContext.user ? "LOG OUT" : "LOG IN"}</button>
//                 </NavLink>

//             </nav>
//         </header>
//     );
// };

// export default Header;
