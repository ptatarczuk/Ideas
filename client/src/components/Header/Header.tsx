import React, { useContext } from "react";

import { UserContext } from "../../context/UserContext";

import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import Container from "@mui/material/Container";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import Tooltip from "@mui/material/Tooltip";
import MenuItem from "@mui/material/MenuItem";
import AdbIcon from "@mui/icons-material/Lightbulb";
import { Link } from "react-router-dom";
import axios from "axios";



const pages = ["Threads", "Users", "Login"]; // TODO: przerobic na enum -> poki co nie wiem jak zrobic mapowanie przez ten enum 
const settings = ["Profile", "Account", "Dashboard", "Logout"];

function ResponsiveAppBar() {
  const userContext = useContext(UserContext);

  const handleLogout = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/v1/auth/logout", {}, {
        headers: {    // TODO: Dodac interceptory zebysmy  nie musieli w kazdym zapytaniu wpisywac headerow 
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
      });
  
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
    <AppBar position="static">
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <AdbIcon sx={{ display: { xs: "none", md: "flex" }, mr: 1 }} />
          <Typography
            variant="h6"
            noWrap
            component="a"
            href="/"
            sx={{
              mr: 2,
              display: { xs: "none", md: "flex" },
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
              color: "inherit",
              textDecoration: "none",
            }}
          >
            Ideas
          </Typography>

          <Box
            sx={{
              flexGrow: 1,

              display: { xs: "flex", md: "none" },

              // display: "flex",
              // justifyContent: "center", // Center horizontally
              // alignItems: "center", // Center vertically
            }}
          >
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton>
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
          </Box>
          <AdbIcon sx={{ display: { xs: "flex", md: "none" }, mr: 1 }} />
          <Typography
            variant="h5"
            noWrap
            component="a"
            href="/"
            sx={{
              mr: 2,
              display: { xs: "flex", md: "none" },
              flexGrow: 1,
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
              color: "inherit",
              textDecoration: "none",
            }}
          >
            LOGO
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
            {pages.map((page) => (
              <Button
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
              </Button>
            ))}
          </Box>

          <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">
              <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                <Avatar alt="Remy Sharp" src="/static/images/avatar/2.jpg" />
              </IconButton>
            </Tooltip>
            <Menu
              sx={{ mt: "45px" }}
              id="menu-appbar"
              anchorEl={anchorElUser}
              anchorOrigin={{
                vertical: "top",
                horizontal: "right",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "right",
              }}
              open={Boolean(anchorElUser)}
              onClose={handleCloseUserMenu}
            >
              {settings.map((setting) => (
                <MenuItem key={setting} onClick={handleCloseUserMenu}>
                  <Typography textAlign="center">{setting}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
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
