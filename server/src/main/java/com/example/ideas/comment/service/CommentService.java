package com.example.ideas.comment.service;

import com.example.ideas.comment.model.Comment;
import com.example.ideas.comment.repository.CommentRepository;
import com.example.ideas.thread.repository.ThreadRepository;
import com.example.ideas.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findCommentByCommentId(Long commentId) {
        return commentRepository.findCommentByCommentId(commentId);
    }

    public Optional<Comment> findCommentByUserId(Long userId) {
        return commentRepository.findCommentByUser_UserId(userId);
    }

    public List<Comment> getCommentsByThreadId(Long threadId) {
        return commentRepository.findCommentsByThreadId(threadId);
    }

    public ResponseEntity<String> addComment(@Valid Comment comment) {  // @valid doesnt work, validation needs to be in service to display proper statuses
        String commentText = comment.getCommentText();
        if (commentText == null || commentText.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comment text cannot be empty");
        }
        commentRepository.save(comment);
        return ResponseEntity.ok("Comment added successfully");
    }

    public ResponseEntity<String> deleteComment(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            commentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Comment with id: " + id + " has been deleted.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Transactional
    public ResponseEntity<String> updateCommentById(Long commentId, Comment updatedComment) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided comment with id: " + commentId + " does not exist");
        }
        if (updatedComment.getCommentText() != null && updatedComment.getCommentText().length() > 0 && !comment.getCommentText().equals(updatedComment.getCommentText())) {
            comment.setCommentText(updatedComment.getCommentText());
            comment.setCommentDate(LocalDate.now());
            return ResponseEntity.ok("Comment updated successfully");
        }
        return ResponseEntity.ok("No changes to comment");
    }
}
