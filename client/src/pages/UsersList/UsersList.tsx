import React, { useEffect, useState } from "react";
import axios from "axios";

import Box from "@mui/material/Box";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select, { SelectChangeEvent } from "@mui/material/Select";

export type Role = {
  roleId: number;
  roleName: string;
};

export type Department = {
  departmentId: number;
  departmentName: string;
};

export type Users = {
  name: string;
  email: string;
  role: Role;
  department: Department;
};

const UsersList: React.FC = () => {
  const [users, setUsers] = useState<Users[]>([]);
  const [roles, setRoles] = useState<Role[] | null>();
  const [selectedRole, setSelectedRole] = useState<number | undefined>(undefined);
  const [filteredUsers, setFilteredUsers] = useState<Users[]>([]);

  const handleRoleChange = (event: SelectChangeEvent) => {
    setSelectedRole(event.target.value !== "" ? parseInt(event.target.value) : undefined);
  };

  useEffect(() => {
    const url = "http://localhost:8080/users/";
    axios.get(url).then((res) => {
      setUsers(res.data);
      setFilteredUsers(res.data);
    });
  }, []);

  useEffect(() => {
    const url = "http://localhost:8080/roles/";
    axios.get(url).then((res) => {
      //console.log(res.data)
      setRoles(res.data);
    });
  }, []);

  useEffect(() => {
    if (selectedRole === undefined) {
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
