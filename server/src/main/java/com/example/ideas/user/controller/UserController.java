package com.example.ideas.user.controller;
import com.example.ideas.exception.DataAlreadyExistsException;
import com.example.ideas.exception.EntityNotFoundException;
import com.example.ideas.exception.NoAuthorizationException;
import com.example.ideas.security.auth.AuthenticationService;
import com.example.ideas.security.auth.RegisterRequest;
import com.example.ideas.user.model.User;
import com.example.ideas.user.service.UserDTOMapper;
import com.example.ideas.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK) ;
    }

    @GetMapping("/id/{user_id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("user_id") Long userId) throws EntityNotFoundException {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable("email") String email) throws EntityNotFoundException {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PatchMapping("/id/{user_id}")
    public ResponseEntity<UserResponseDTO> updateUserById(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable("user_id") Long userId,
            @RequestBody @Valid UserUpdateRequest request
    ) throws DataAlreadyExistsException, EntityNotFoundException, IllegalAccessException, NoAuthorizationException {
        return ResponseEntity.ok(userService.updateUserById(userId, request, token));
    }

//    @PatchMapping("/email/{user_email}")
//    public ResponseEntity<String> updateUserByEmail(
//            @PathVariable("user_email") String email,
//            @RequestBody User updatedUser
//    ) {
//        return userService.updateUserByEmail(email, updatedUser);
//    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long userId) {
        userService.deleteUser(userId);
    }

}