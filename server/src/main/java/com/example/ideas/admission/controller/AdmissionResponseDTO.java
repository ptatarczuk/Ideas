package com.example.ideas.admission.controller;

import java.time.LocalDate;

public record AdmissionResponseDTO(
        Long admissionId,
        String content,
        LocalDate dateOfPost,
        Long userId,
        Long threadId
) {
}
