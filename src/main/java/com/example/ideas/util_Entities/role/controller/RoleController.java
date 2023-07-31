package com.example.ideas.util_Entities.role.controller;

import com.example.ideas.util_Entities.role.model.Role;
import com.example.ideas.util_Entities.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/roles")
@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/name/{role_name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable("role_name") String roleName) {
        Role role = roleService.getRoleByName(roleName).orElse(null);
        return role != null ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{role_id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("role_id") Long roleId) {
        Role role = roleService.getRoleById(roleId).orElse(null);
        return role != null ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
    }
}
