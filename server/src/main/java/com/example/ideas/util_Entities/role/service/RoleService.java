package com.example.ideas.util_Entities.role.service;

import com.example.ideas.util_Entities.role.model.Role;
import com.example.ideas.util_Entities.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll(Sort.by(Sort.Direction.ASC, "roleId"));
    }

    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    public Optional<Role> getRoleByName(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }
}
