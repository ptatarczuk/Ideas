package com.example.ideas.util_Entities.department.controller;

import com.example.ideas.util_Entities.department.model.Department;
import com.example.ideas.util_Entities.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/departments")
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("/name/{department_name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable("department_name") String departmentName) {
        Department department = departmentService.getDepartmentByName(departmentName).orElse(null);
        return department != null ? ResponseEntity.ok(department) : ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{department_id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("department_id") Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId).orElse(null);
        return department != null ? ResponseEntity.ok(department) : ResponseEntity.notFound().build();
    }
}
