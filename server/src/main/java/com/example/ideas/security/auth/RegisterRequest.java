package com.example.ideas.security.auth;

import com.example.ideas.util_Entities.department.model.Department;
import com.example.ideas.util_Entities.role.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "name cannot be blank")
    private String name;
    @Email(message = "incorrect email")
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
    private Role role;
    private Department department;
}
