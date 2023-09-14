package com.example.ideas.admission.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdmissionCreateDTO {

    @NotBlank(message = "Content of admission information is mandatory")
    private String content;

    private Long userId;

    private Long threadId;

    private Long stageId;
}
