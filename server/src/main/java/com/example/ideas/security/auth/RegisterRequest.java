package com.example.ideas.security.auth;

import com.example.ideas.util_Entities.department.model.Department;
import com.example.ideas.util_Entities.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
    private Department department;
}
