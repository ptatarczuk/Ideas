package com.example.ideas.util_Entities.department.repository;

import com.example.ideas.util_Entities.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findDepartmentByDepartmentName(String departmentName);
}
