package com.example.ideas.security.auth;

import com.example.ideas.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/change-password")
    public ResponseEntity<AuthenticationResponse> changePassword(
            @RequestBody PasswordChangeRequest request
    ) {
        return authenticationService.changePassword(request);
    }

    @PostMapping("reset-password")
    public ResponseEntity<String> resetPassword(
            HttpServletRequest request,
            @RequestParam("email") String userEmail
    ) {
        //String baseUrl = request.getRequestURL().toString();
        String baseUrl = "http://localhost:3000/reset-password";
        return authenticationService.resetPassword(userEmail, baseUrl);
    }

    @PatchMapping("reset-password/{token}")
    public ResponseEntity<String> changePassword(
            @PathVariable("token") String token,
            @RequestBody String newPassword
    ) {
        System.out.println(newPassword);
        String trimmedPassword = newPassword.substring(1, newPassword.length() - 1);
        System.out.println(trimmedPassword);
        return authenticationService.changePassword(token, trimmedPassword);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }


}
