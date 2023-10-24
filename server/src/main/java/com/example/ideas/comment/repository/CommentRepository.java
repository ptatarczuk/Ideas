package com.example.ideas.comment.repository;

import com.example.ideas.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByUser_UserId(Long userId);

    @Query("SELECT c FROM Comment c WHERE c.thread.threadId = :threadId")
    List<Comment> findCommentsByThreadId(@Param("threadId") Long threadId);

}