package com.example.ideas.opinionTwo.controller;

import com.example.ideas.opinionOne.service.OpinionOneService;
import com.example.ideas.opinionTwo.service.OpinionTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/opinion_two")
@RestController
public class OpinionTwoController {

    @Autowired
    private OpinionTwoService opinionTwoService;

    public OpinionTwoController(OpinionTwoService opinionTwoService) {
        this.opinionTwoService = opinionTwoService;
    }

    @GetMapping("/{opinion_two_id}")
    public void getOpinionTwoById() {

    }

    @GetMapping("/thread/{thread_id}")
    public void getOpinionTwoByThread() {

    }

    @PostMapping("/add")
    public void addOpinionTwo() {

    }

    @PutMapping("/{opinion_two_id}")
    public void updateOpinionTwoById() {

    }

    @DeleteMapping("/{opinion_two_id}")
    public void deleteOpinionTwoById() {

    }

}
