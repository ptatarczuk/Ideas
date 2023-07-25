package com.example.ideas.user.service;

import com.example.ideas.department.model.Department;
import com.example.ideas.department.repository.DepartmentRepository;
import com.example.ideas.role.model.Role;
import com.example.ideas.role.repository.RoleRepository;
import com.example.ideas.user.model.User;
import com.example.ideas.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "email"));
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public ResponseEntity<String> addUser(@Valid User user) {
        String email = user.getEmail();
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already taken");
        }
        if (!isEmailValid(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format");
        }
        Long roleId = user.getRole().getRoleId();
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role with id: " + roleId + " doesn't exist");
        }
        Long departmentId = user.getDepartment().getDepartmentId();
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department with id: " + departmentId + " doesn't exist");
        }
        Role role = roleOptional.get();
        Department department = departmentOptional.get();

        userRepository.save(new User(user.getName(), email, user.getPassword(), role, department));
        return ResponseEntity.ok("User added successfully");
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("student with id: " + userId + " does not exists");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public ResponseEntity<String> updateUserById(Long userId, User updatedUser) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + userId + " not found");
        }
        return validation(updatedUser.getEmail(), updatedUser.getName(), updatedUser.getPassword(), updatedUser.getRole().getRoleId(), updatedUser.getDepartment().getDepartmentId(), user);
    }
    @Transactional
    public ResponseEntity<String> updateUserByEmail(String email, User updatedUser) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + email + " not found");
        }
        return validation(updatedUser.getEmail(), updatedUser.getName(), updatedUser.getPassword(), updatedUser.getRole().getRoleId(), updatedUser.getDepartment().getDepartmentId(), user);
    }

    private ResponseEntity<String> validation(String email, String name, String password, Long roleId, Long departmentId, User user) {
        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) { // user.getEmail().equals(email)
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already taken");
            }
            user.setEmail(email);
        }
        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }
        if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }
        if (roleId != null && roleId > 0 && !Objects.equals(user.getRole().getRoleId(), roleId)) {
            Optional<Role> roleOptional = roleRepository.findById(roleId);
            if (roleOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role with id: " + roleId + " doesn't exist");
            }
            user.setRole(roleOptional.get());
        }
        if (departmentId != null && departmentId > 0 && !Objects.equals(user.getDepartment().getDepartmentId(), departmentId)) {
            Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
            if (departmentOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department with id: " + departmentId + " doesn't exist");
            }
            user.setDepartment(departmentOptional.get());
        }
        return ResponseEntity.ok("Validation successful");
    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}