package com.example.ideas.comment.controller;


import com.example.ideas.comment.model.Comment;
import com.example.ideas.comment.service.CommentService;
import com.example.ideas.exception.EntityNotFoundException;
import com.example.ideas.exception.NoAuthorizationException;
import com.example.ideas.thread.service.ThreadService;
import com.example.ideas.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/comments")
@RestController
public class CommentController {
    private CommentService commentService;
    private UserService userService;
    private ThreadService threadService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService, ThreadService threadService) {
        this.commentService = commentService;
        this.userService = userService;
        this.threadService = threadService;
    }


    @GetMapping("/")
    public List<Comment> getComments() {
        return commentService.getComments();

    }

    @GetMapping("/{comment_id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("comment_id") Long commentId) {
        Comment comment = commentService.findCommentByCommentId(commentId).orElse(null);
        return comment != null ? ResponseEntity.ok(comment) : ResponseEntity.notFound().build();

    }

//    @GetMapping("/user/{user_id}")
//    public ResponseEntity<Comment> getCommentByUserId(@PathVariable("user_id") Long userId) {
//        Comment comment = commentService.findCommentByUserId(userId).orElse(null);
//        return comment != null ? ResponseEntity.ok(comment) : ResponseEntity.notFound().build();
//    }

    @GetMapping("/thread/{thread_id}")
    public List<Comment> getCommentByThreadId(@PathVariable("thread_id") Long threadId) {
        return commentService.getCommentsByThreadId(threadId);
    }

    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(@Valid @RequestBody CommentCreateDTO commentCreateDTO) throws EntityNotFoundException {
        return new ResponseEntity<>(commentService.addComment(commentCreateDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/update_comment/{comment_id}")
    public ResponseEntity<String> updateCommentById(
            @PathVariable("comment_id") Long commentId,

            @RequestBody Comment updatedComment
    ) {
        return commentService.updateCommentById(commentId, updatedComment);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<Boolean> deleteCommentById(
            @PathVariable("comment_id") Long commentId,
            @RequestHeader("Authorization") String token
    ) throws NoAuthorizationException, EntityNotFoundException {
        return new ResponseEntity<>(commentService.deleteComment(token, commentId), HttpStatus.OK);
    }

}
