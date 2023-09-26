package com.example.ideas.util_Entities.role.repository;

import com.example.ideas.util_Entities.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(String roleName);
    Optional<Role> findRoleByRoleId(Long roleId);
}
