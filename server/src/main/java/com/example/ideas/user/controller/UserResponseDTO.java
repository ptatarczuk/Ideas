package com.example.ideas.user.controller;

import com.example.ideas.util_Entities.department.model.Department;

import java.util.List;

public record UserResponseDTO(
        Long userId,
        String name,
        String email,
        List<String> roles,
        Department department
) {
}
