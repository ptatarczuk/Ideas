package com.example.ideas.thread.controller;

import com.example.ideas.admission.model.Admission;
import com.example.ideas.conclusion.model.Conclusion;
import com.example.ideas.util_Entities.category.model.Category;
import com.example.ideas.util_Entities.stage.model.Stage;
import com.example.ideas.util_Entities.status.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ThreadUpdateDTO {
    private String email;
    private String title;
    private String description;
    private String justification;
    private String photo;
    private Integer points; //zrobić osobny mechanizm
    private Category category;
    private Stage stage;
    private Status status;
    private Admission admission;
    private Conclusion conclusion;
}
