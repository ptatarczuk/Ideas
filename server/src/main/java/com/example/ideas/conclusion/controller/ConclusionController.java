package com.example.ideas.conclusion.controller;

import com.example.ideas.admission.controller.AdmissionCreateDTO;
import com.example.ideas.admission.controller.AdmissionResponseDTO;
import com.example.ideas.admission.controller.AdmissionUpdateDTO;
import com.example.ideas.admission.model.Admission;
import com.example.ideas.conclusion.model.Conclusion;
import com.example.ideas.conclusion.service.ConclusionService;
import com.example.ideas.exception.DataAlreadyExistsException;
import com.example.ideas.exception.EntityNotFoundException;
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
    public ResponseEntity<ConclusionResponseDTO> getConclusionById(@PathVariable("conclusion_id") Long conclusionId) throws EntityNotFoundException {
        return ResponseEntity.ok(conclusionService.getConclusionById(conclusionId));
    }

    @PostMapping("/add")
    public ResponseEntity<ConclusionResponseDTO> addConclusion(@Valid @RequestBody ConclusionCreateDTO conclusionCreateDTO)
            throws DataAlreadyExistsException, EntityNotFoundException {

        return new ResponseEntity<>(conclusionService.addConclusion(conclusionCreateDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{conclusion_id}")
    public ResponseEntity<ConclusionResponseDTO> updateConclusionById(
            @PathVariable("conclusion_id") Long conclusionId,
            @RequestBody ConclusionUpdateDTO conclusionUpdateDTO
    ) throws EntityNotFoundException {
        return ResponseEntity.ok(conclusionService.updateConclusionById(conclusionId, conclusionUpdateDTO));
    }


    @DeleteMapping("/{conclusion_id}")
    public ResponseEntity<String> deleteConclusionById(@PathVariable("conclusion_id") Long conclusionId) {
        return conclusionService.deleteConclusionById(conclusionId);
    }

}
