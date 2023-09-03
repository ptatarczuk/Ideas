import React, { useEffect, useState } from "react";
import axios from "axios";

import Box from "@mui/material/Box";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select, { SelectChangeEvent } from "@mui/material/Select";

import {Role} from "../../models/Role"
import {User} from "../../models/User"
import {Department} from "../../models/Department" // TODO : Czy dodac filtrowanie przez departament ?


const UsersList: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [roles, setRoles] = useState<Role[] | null>();
  const [selectedRole, setSelectedRole] = useState<number | undefined>();
  const [filteredUsers, setFilteredUsers] = useState<User[]>([]);

  const handleRoleChange = (event: SelectChangeEvent) => {
    setSelectedRole(event.target.value ? parseInt(event.target.value) : undefined);
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
      const filtered = users.filter((user) => user.role.roleId === selectedRole);
      setFilteredUsers(filtered);
      console.log(selectedRole);
    }
  }, [selectedRole, users]);

  return (
    <div>
      <Box sx={{ minWidth: 120, maxWidth: 200, paddingTop: 1 }}>
        <FormControl fullWidth>
          <InputLabel id="role-select-label">Select Role</InputLabel>
          <Select
            labelId="role-select-label"
            id="role-select"
            value={selectedRole?.toString() || ""}
            label="Role"
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

      {filteredUsers.map((user) => (
        <p key={user.email}>
          {user.name +
            " " +
            user.email +
            " " +
            user.role.roleName +
            " " +
            user.department.departmentName}
        </p>
      ))}
    </div>
  );
};

export default UsersList;
