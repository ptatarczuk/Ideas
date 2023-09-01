package com.example.ideas.thread.controller;

import com.example.ideas.admission.model.Admission;
import com.example.ideas.comment.model.Comment;
import com.example.ideas.conclusion.model.Conclusion;
import com.example.ideas.user.model.User;
import com.example.ideas.util_Entities.category.model.Category;
import com.example.ideas.util_Entities.stage.model.Stage;
import com.example.ideas.util_Entities.status.model.Status;
import com.example.ideas.util_Entities.vote.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class ThreadDTO {
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
