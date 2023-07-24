package com.example.ideas.user.config;
import com.example.ideas.department.repository.DepartmentRepository;
import com.example.ideas.role.repository.RoleRepository;
import com.example.ideas.user.model.User;
import com.example.ideas.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
@Configuration
public class UserConfig {
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public UserConfig(RoleRepository roleRepository, DepartmentRepository departmentRepository) {
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
    }
    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository repository) {
        return args -> {
            User jowita = new User(999L,"Jowita", "jowita@test.com","123", roleRepository.findById(1L).get(), departmentRepository.findById(5L).get());
            User alex = new User(999L, "Alex", "alex@test.com", "321", roleRepository.findById(4L).get(), departmentRepository.findById(9L).get());
            User tomek = new User(999L, "Tomek", "tomek@test.com", "555", roleRepository.findById(3L).get(), departmentRepository.findById(3L).get());
            User piotr = new User(999L, "Piotr", "piotr@test.com", "987", roleRepository.findById(2L).get(), departmentRepository.findById(4L).get());
            repository.saveAll(List.of(jowita, alex, tomek, piotr));
        };
    }
}
