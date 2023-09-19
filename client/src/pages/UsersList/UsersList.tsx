import React, { useEffect, useState } from "react";
import axios from "axios";

import Box from "@mui/material/Box";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select, { SelectChangeEvent } from "@mui/material/Select";

import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";

import { Role } from "../../models/Role";
import { User } from "../../models/User";
import { Department } from "../../models/Department"; // TODO : Czy dodac filtrowanie przez departament ?
import { Card } from "@mui/material";

const UsersList: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [roles, setRoles] = useState<Role[] | null>();
  const [selectedRole, setSelectedRole] = useState<number | undefined>();
  const [filteredUsers, setFilteredUsers] = useState<User[]>([]);

  const handleRoleChange = (event: SelectChangeEvent) => {
    setSelectedRole(
      event.target.value ? parseInt(event.target.value) : undefined
    );
  };

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const url = "http://localhost:8080/users/";
        const response = await axios.get(url);
        setUsers(response.data);
        setFilteredUsers(response.data);
      } catch (error) {
        console.error("Error fetching users:", error);
      }
    };

    fetchUsers();
  }, []);

  useEffect(() => {
    const fetchRoles = async () => {
      try {
        const url = "http://localhost:8080/roles/";
        const response = await axios.get(url);
        setRoles(response.data);
      } catch (error) {
        console.error("Error fetching roles:", error);
      }
    };

    fetchRoles();
  }, []);

  useEffect(() => {
    if (!selectedRole) {
      setFilteredUsers(users);
    } else {
      const filtered = users.filter(
        (user) => user.role.roleId === selectedRole
      );
      setFilteredUsers(filtered);
      console.log(selectedRole);
    }
  }, [selectedRole, users]);

  return (
    <div>
      <Box sx={{ minWidth: 120, maxWidth: 200, marginLeft: '10%', paddingTop: 1 }}>
        <FormControl fullWidth>
          <InputLabel id="role-select-label">Select Role</InputLabel>
          <Select
            labelId="role-select-label"
            id="role-select"
            value={selectedRole?.toString() || ""}
            label="Select Role"
            onChange={handleRoleChange}
          >
            {roles
              ? roles.map((role) => (
                  <MenuItem key={role.roleId} value={role.roleId}>
                    {role.roleName}
                  </MenuItem>
                ))
              : null}
          </Select>
        </FormControl>
      </Box>

      <TableContainer component={Paper}>
        <Table
          sx={{ minWidth: 650, marginLeft: '10%', marginRight: '10%', maxWidth: '80%'  }} // remove margins from here and add css file later
          aria-label="simple table"
        >
          <TableHead>
            <TableRow>
              <TableCell>User Name</TableCell>
              <TableCell align="right">Email</TableCell>
              <TableCell align="right">Role</TableCell>
              <TableCell align="right">Department</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredUsers.map((user) => (
              <TableRow
                key={user.email}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {user.name}
                </TableCell>
                <TableCell align="right">{user.email}</TableCell>
                <TableCell align="right">{user.role.roleName}</TableCell>
                <TableCell align="right">
                  {user.department.departmentName}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default UsersList;
