package com.example.ideas.conclusion.controller;

import com.example.ideas.conclusion.model.Conclusion;
import com.example.ideas.conclusion.service.ConclusionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/conclusion")
@RestController
public class ConclusionController {
    private final ConclusionService conclusionService;

    @Autowired
    public ConclusionController(ConclusionService conclusionService) {
        this.conclusionService = conclusionService;
    }

    @GetMapping("/{conclusion_id}")
    public ResponseEntity<Conclusion> getConclusionById(@PathVariable("conclusion_id") Long conclusionId) {
        Conclusion conclusion = conclusionService.getConclusionById(conclusionId).orElse(null);
        return conclusion != null ? ResponseEntity.ok(conclusion) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addConclusion(@Valid @RequestBody Conclusion conclusion) {
        return conclusionService.addConclusion(conclusion);
    }

    @PutMapping("/{conclusion_id}")
    public ResponseEntity<String> updateConclusionById(
            @PathVariable("conclusion_id") Long conclusionId,
            @RequestBody Conclusion updatedConclusion
    ) {
        return conclusionService.updateConclusionById(conclusionId, updatedConclusion);
    }

    @DeleteMapping("/{conclusion_id}")
    public ResponseEntity<String> deleteConclusionById(@PathVariable("conclusion_id") Long conclusionId) {
        return conclusionService.deleteConclusionById(conclusionId);
    }

}
