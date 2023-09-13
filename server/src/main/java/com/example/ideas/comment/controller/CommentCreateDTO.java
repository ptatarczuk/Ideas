package com.example.ideas.comment.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentCreateDTO {
    @NotBlank(message = "comment cannot be blank")
    private String commentText;
    private Long userId;
    private Long threadId;
}
