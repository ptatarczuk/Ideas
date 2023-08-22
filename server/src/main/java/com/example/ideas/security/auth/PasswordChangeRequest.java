package com.example.ideas.security.auth;


import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeRequest extends AuthenticationRequest {

    private String newPassword;
}
