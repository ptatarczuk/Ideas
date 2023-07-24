package com.example.ideas.user.controller;
import com.example.ideas.user.model.User;
import com.example.ideas.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/id/{user_id}")
    public ResponseEntity<User> getRoleById(@PathVariable("user_id") Long userId) {
        User user = userService.getUserById(userId).orElse(null);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getRoleByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email).orElse(null);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/id/{user_id}")
    public void updateUserById(
            @PathVariable("user_id") Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Long roleId,
            @RequestParam(required = false) Long departmentId
    ){
        userService.updateUserById(userId, name, email, password, roleId, departmentId);
    }

    @PutMapping("/email/{user_email}")
    public void updateUserByEmail(
            @PathVariable("user_email") String email,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Long roleId,
            @RequestParam(required = false) Long departmentId
    ) {
        userService.updateUserByEmail(email, name, password, roleId, departmentId);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long userId) {
        userService.deleteUser(userId);
    }

}