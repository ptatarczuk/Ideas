package com.example.ideas.thread.controller;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class ThreadUpdateDTO {
    @Email(message = "invalid email address")
    private String email;
    private String title;
    private String description;
    private String justification;
    private Integer points; //zrobić osobny mechanizm
    private Long categoryId;
    private Long stageId;
    private Long statusId;

}
