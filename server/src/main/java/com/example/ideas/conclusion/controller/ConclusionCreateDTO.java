package com.example.ideas.conclusion.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ConclusionCreateDTO {

    @NotBlank(message = "Content of conclusion information is mandatory")
    private String content;

    private Long userId;

    private Long threadId;

    private Long stageId;
}
