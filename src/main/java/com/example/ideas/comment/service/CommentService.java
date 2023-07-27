package com.example.ideas.comment.service;

import com.example.ideas.comment.model.Comment;
import com.example.ideas.comment.repository.CommentRepository;
import com.example.ideas.thread.repository.ThreadRepository;
import com.example.ideas.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private ThreadRepository threadRepository;
@Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, ThreadRepository threadRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.threadRepository = threadRepository;
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
}
