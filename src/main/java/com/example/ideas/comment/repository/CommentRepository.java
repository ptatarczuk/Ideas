package com.example.ideas.comment.repository;

import com.example.ideas.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentByCommentId(Long commentId);
    Optional<Comment> findCommentByUser_UserId(Long userId);

}