package com.example.ideas.admission.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdmissionUpdateDTO {

    private String content;

    private Long userId;

    private Long stageId;
}
