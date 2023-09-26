package com.example.ideas.conclusion.controller;

import java.time.LocalDate;

public record ConclusionResponseDTO(
        Long conclusionId,
        String content,
        LocalDate dateOfPost,
        Long userId,
        Long threadId
) {
}
