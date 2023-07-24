package com.example.ideas.comment.controller;


import com.example.ideas.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comments")
@RestController
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/")
    public void getComments() {

    }

    @GetMapping("/{comment_id}")
    public void getCommentById() {

    }

    @PostMapping("/addComment")
    public void addComment() {

    }

    @PutMapping("/update_comment/{comment_id}")
    public void updateCommentById() {

    }

    @DeleteMapping("/{comment_id}")
    public void deleteCommentById() {

    }

}
