//package com.example.ideas.department.config;
//import com.example.ideas.department.model.Department;
//import com.example.ideas.department.repository.DepartmentRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.util.List;
//@Configuration
//public class DepartmentConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerDepartment(DepartmentRepository repository) {
//        return args -> {
//            Department d1 = new Department(1L, "Department of Strategic Planning");
//            Department d2 = new Department(2L, "Human Resources Division");
//            Department d3 = new Department(3L, "Finance and Accounting Department");
//            Department d4 = new Department(4L, "Research and Development Team");
//            Department d5 = new Department(5L, "Marketing and Communications Bureau");
//            Department d6 = new Department(6L, "Information Technology Division");
//            Department d7 = new Department(7L, "Operations and Logistics Unit");
//            Department d8 = new Department(8L, "Customer Relations Department");
//            Department d9 = new Department(9L, "Quality Assurance and Compliance Team");
//            Department d10 = new Department(10L, "Procurement and Supply Chain Division");
//            repository.saveAll(List.of(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10));
//        };
//    }
//}
