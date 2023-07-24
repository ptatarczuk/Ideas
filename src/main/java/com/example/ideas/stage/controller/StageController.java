package com.example.ideas.stage.controller;

import com.example.ideas.stage.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/stages")
@RestController
public class StageController {

    private StageService stageService;

    @Autowired
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping("/")
    public void getStages(){

    }

    @GetMapping("/name/{stage_name}")
    public void getStageByName() {

    }

    @GetMapping("/id/{stage_id}")
    public void getStageById() {

    }
}
