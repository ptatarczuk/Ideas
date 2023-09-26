package com.example.ideas.comment.service;

import com.example.ideas.comment.controller.CommentResponseDTO;
import com.example.ideas.comment.model.Comment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CommentDTOMapper implements Function<Comment, CommentResponseDTO> {
    @Override
    public CommentResponseDTO apply(Comment comment) {
        return new CommentResponseDTO(
                comment.getCommentId(),
                comment.getCommentText(),
                comment.getCommentDate(),
                comment.getUser().getUserId(),
                comment.getThread().getThreadId()
        );
    }
}
