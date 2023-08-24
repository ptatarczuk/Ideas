package com.example.ideas.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

   Optional<PasswordResetToken> findPasswordResetTokenByToken(String token);
}
