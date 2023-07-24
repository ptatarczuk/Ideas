package com.example.ideas.opinionOne.controller;

import com.example.ideas.opinionOne.service.OpinionOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/opinion_one")
@RestController
public class OpinionOneController {

    private OpinionOneService opinionOneService;

    @Autowired
    public OpinionOneController(OpinionOneService opinionOneService) {
        this.opinionOneService = opinionOneService;
    }

    @GetMapping("/{opinion_one_id}")
    public void getOpinionOneById() {

    }

    @GetMapping("/thread/{thread_id}")
    public void getOpinionOneByThread() {

    }

    @PostMapping("/add")
    public void addOpinionOne() {

    }

    @PutMapping("/{opinion_one_id}")
    public void updateOpinionOneById() {

    }

    @DeleteMapping("/{opinion_one_id}")
    public void deleteOpinionOneById() {

    }
}
