package com.example.ideas.conclusion.service;

import com.example.ideas.conclusion.model.Conclusion;
import com.example.ideas.conclusion.repository.ConclusionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ConclusionService {

    private final ConclusionRepository conclusionRepository;

    @Autowired
    public ConclusionService(ConclusionRepository conclusionRepository) {
        this.conclusionRepository = conclusionRepository;
    }

    public Optional<Conclusion> getConclusionById(Long conclusionId) {
        return conclusionRepository.findById(conclusionId);
    }

    public ResponseEntity<String> addConclusion(@Valid Conclusion conclusion) {
        if (conclusion.getContent().isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conclusion content cannot be empty.");
        }
        conclusionRepository.save(new Conclusion(conclusion.getContent(), conclusion.getPoints()));
        return ResponseEntity.ok("Conclusion added successfully");
    }

    @Transactional
    public ResponseEntity<String> updateConclusionById(Long conclusionId, Conclusion updatedConclusion) {
        Conclusion searchedConclusion = conclusionRepository.findById(conclusionId).orElse(null);
        if (searchedConclusion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided Conclusion with id:" + conclusionId + " does not exist");
        }
        if (updatedConclusion.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Content cannot be empty");
        }
        if (updatedConclusion.getPoints() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Awarded points cannot be lower than 0");
        }
        searchedConclusion.setContent(updatedConclusion.getContent());
        searchedConclusion.setPoints(updatedConclusion.getPoints());
        return ResponseEntity.ok("Conclusion updated successfully");
    }

    public ResponseEntity<String> deleteConclusionById(Long conclusionId) {
        if (conclusionRepository.existsById(conclusionId)) {
            conclusionRepository.deleteById(conclusionId);
            return ResponseEntity.ok("Admission with id: " + conclusionId + " deleted successfully.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conclusion with id: " + conclusionId + " doesn't exist.");
    }


}
