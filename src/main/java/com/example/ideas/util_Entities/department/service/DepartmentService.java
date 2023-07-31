package com.example.ideas.util_Entities.department.service;

import com.example.ideas.util_Entities.department.model.Department;
import com.example.ideas.util_Entities.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll(Sort.by(Sort.Direction.ASC, "departmentId"));
    }

    public Optional<Department> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    public Optional<Department> getDepartmentByName(String departmentName) {
        return departmentRepository.findDepartmentByDepartmentName(departmentName);
    }
}
