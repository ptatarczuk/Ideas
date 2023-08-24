package com.example.ideas.security.auth;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeRequest {

    private String email;
    private String password;
    private String newPassword;
}
