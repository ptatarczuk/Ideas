package com.example.ideas.role.repository;

import com.example.ideas.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(String roleName);
    //Optional<Role> findRoleByRoleId(Long roleId);
}
