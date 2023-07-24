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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return userRepository.findAll(/*Sort.by(Sort.Direction.ASC, "user_name")*/);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public  void addUser(@Valid User user) {
        String email = user.getEmail();
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already taken");
        }
        Long roleId = user.getRole().getRoleId();
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with id: " + roleId + " doesn't exist");
        }
        Long departmentId = user.getDepartment().getDepartmentId();
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with id: " + departmentId + " doesn't exist");
        }
        Role role = roleOptional.get();
        Department department = departmentOptional.get();

        userRepository.save(new User(user.getName(), email, user.getPassword(), role, department));
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("student with id: " + userId + " does not exists");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUserById(Long userId, String name, String email, String password, Long roleId, Long departmentId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with id: " + userId + " does not exist"));
        validation(email, name, password, roleId, departmentId, user);
    }

    @Transactional
    public void updateUserByEmail(String email, String name, String password, Long roleId, Long departmentId) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new IllegalStateException("User with email: " + email + " does not exist"));
        validation(email, name, password, roleId, departmentId, user);
    }

    private void validation(String email, String name, String password, Long roleId, Long departmentId, User user) {
        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) { // user.getEmail().equals(email)
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("email is already taken");
            }
            user.setEmail(email);
        }
        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) { user.setName(name);  }
        if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) { user.setPassword(password); }
        if (roleId != null && !Objects.equals(user.getRole().getRoleId(), roleId)) {
            Optional<Role> roleOptional = roleRepository.findById(roleId);
            Role role = roleOptional.orElseThrow(() -> new IllegalStateException("Role with id: " + roleId + " does not exist"));
            user.setRole(role);
        }
        if (departmentId != null && !Objects.equals(user.getDepartment().getDepartmentId(), departmentId)) {
            Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
            Department department = departmentOptional.orElseThrow(() -> new IllegalStateException("Department with id: " + departmentId + " does not exist"));
            user.setDepartment(department);
        }
    }
}