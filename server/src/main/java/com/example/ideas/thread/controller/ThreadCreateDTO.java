package com.example.ideas.thread.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ThreadCreateDTO {

    private String title;
    private String description;
    private String justification;
    private String userEmail;
    private Long categoryId;
}
