package com.example.ideas.thread.controller;

import com.example.ideas.admission.controller.AdmissionResponseDTO;
import com.example.ideas.comment.controller.CommentResponseDTO;
import com.example.ideas.conclusion.controller.ConclusionResponseDTO;
import com.example.ideas.user.controller.UserResponseDTO;
import com.example.ideas.util_Entities.category.model.Category;
import com.example.ideas.util_Entities.stage.model.Stage;
import com.example.ideas.util_Entities.status.model.Status;
import com.example.ideas.util_Entities.vote.controller.VoteResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record ThreadResponseDTO(
        Long threadId,
        LocalDate date,
        String title,
        String description,
        String justification,
        String photo,
        Integer points,
        UserResponseDTO user,
        Category category,
        Stage stage,
        Status status,
        List<CommentResponseDTO> comments,
        List<VoteResponseDTO> votes,
        AdmissionResponseDTO admission,
        ConclusionResponseDTO conclusion
) {
}
