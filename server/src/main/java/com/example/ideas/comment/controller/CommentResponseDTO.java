package com.example.ideas.comment.controller;

import java.time.LocalDate;

public record CommentResponseDTO(
        Long commentId,
        String commentText,
        LocalDate commentDate,
        Long commentAuthorId,
        Long threadId
) {
}
