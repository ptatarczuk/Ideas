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
    private final CommentService commentService;

    public CommentController(CommentService commentService, UserService userService, ThreadService threadService) {
        this.commentService = commentService;
    }


    @GetMapping("/")
    public List<CommentResponseDTO> getComments() {
        return commentService.getComments();

    }

    @GetMapping("/{comment_id}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable("comment_id") Long commentId) throws EntityNotFoundException {
       return ResponseEntity.ok(commentService.findCommentByCommentId(commentId));

    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByUserId(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(commentService.findCommentsByUserId(userId));
    }

    @GetMapping("/thread/{thread_id}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByThreadId(@PathVariable("thread_id") Long threadId) {
        return ResponseEntity.ok(commentService.getCommentsByThreadId(threadId));
    }

    @PostMapping("/addComment")
    public ResponseEntity<CommentResponseDTO> addComment(@Valid @RequestBody CommentCreateDTO commentCreateDTO) throws EntityNotFoundException {
        return new ResponseEntity<>(commentService.addComment(commentCreateDTO), HttpStatus.CREATED);
    }

//    @PatchMapping("/update_comment/{comment_id}")
//    public ResponseEntity<String> updateCommentById(
//            @PathVariable("comment_id") Long commentId,
//
//            @RequestBody Comment updatedComment
//    ) {
//        return commentService.updateCommentById(commentId, updatedComment);
//    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<Boolean> deleteCommentById(
            @PathVariable("comment_id") Long commentId,
            @RequestHeader("Authorization") String token
    ) throws NoAuthorizationException, EntityNotFoundException {
        return new ResponseEntity<>(commentService.deleteComment(
                token,
                commentId), HttpStatus.OK);
    }

}
