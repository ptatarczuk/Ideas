package com.example.ideas.role.config;

import com.example.ideas.role.model.Role;
import com.example.ideas.role.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleConfig {

    @Bean
    CommandLineRunner commandLineRunnerRole(RoleRepository repository) {
        return args -> {
            Role admin = new Role(1L, "Admin");
            Role hr = new Role(2L, "HumanResources");
            Role moderator = new Role(3L, "Moderator");
            Role employee = new Role(4L, "Employee");
            repository.saveAll(List.of(admin, hr, moderator, employee));
        };
    }
}
