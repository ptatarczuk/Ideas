package com.example.ideas.util_Entities.stage.controller;

import com.example.ideas.util_Entities.stage.model.Stage;
import com.example.ideas.util_Entities.stage.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/stages")
@RestController
public class StageController {

    private final StageService stageService;

    @Autowired
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping("/")
    public List<Stage> getStages(){
        return stageService.getStages();
    }

    @GetMapping("/name/{stage_name}")
    public ResponseEntity<Stage> getStageByName(@PathVariable("stage_name") String stageName) {
        Stage stage = stageService.getStageByName(stageName).orElse(null);
        return stage != null ? ResponseEntity.ok(stage) : ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{stage_id}")
    public ResponseEntity<Stage> getStageById(@PathVariable("stage_id") Long stageId) {
        Stage stage = stageService.getStageById(stageId).orElse(null);
        return stage != null ? ResponseEntity.ok(stage) : ResponseEntity.notFound().build();
    }
}
