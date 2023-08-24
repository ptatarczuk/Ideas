package com.example.ideas.user.controller;
import com.example.ideas.security.auth.AuthenticationService;
import com.example.ideas.user.model.User;
import com.example.ideas.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


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

    @PatchMapping("/id/{user_id}")
    public ResponseEntity<String> updateUserById(
            @PathVariable("user_id") Long userId,
            @RequestBody User updatedUser
    ) {
        return userService.updateUserById(userId, updatedUser);
    }

    @PatchMapping("/email/{user_email}")
    public ResponseEntity<String> updateUserByEmail(
            @PathVariable("user_email") String email,
            @RequestBody User updatedUser
    ) {
        return userService.updateUserByEmail(email, updatedUser);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long userId) {
        userService.deleteUser(userId);
    }

}