package com.example.ideas.admission.service;

import com.example.ideas.admission.model.Admission;
import com.example.ideas.admission.repository.AdmissionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmissionService {

    private final AdmissionRepository admissionRepository;

    @Autowired
    public AdmissionService(AdmissionRepository admissionRepository) {
        this.admissionRepository = admissionRepository;
    }

    public Optional<Admission> getAdmissionById(Long admissionId) {
        return admissionRepository.findById(admissionId);
    }


    public ResponseEntity<String> addAdmission(@Valid Admission admission) {
        // czy można zapisać to jako .isPresent()
        if (!admission.getContent().isEmpty()) {
            admissionRepository.save(new Admission(admission.getContent()));
            return ResponseEntity.ok("Admission added successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admission content is empty.");
    }

    @Transactional
    public ResponseEntity<String> updateAdmissionById(Long admissionId, Admission updatedAdmission) {
        Admission searchedAdmission = admissionRepository.findById(admissionId).orElse(null);
        if (searchedAdmission == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Searched Admission with id: " + admissionId + " not found");
        }
        return validation(updatedAdmission.getContent(), searchedAdmission);
    }

    public ResponseEntity<String> deleteAdmissionById(Long admissionId) {
        if (admissionRepository.existsById(admissionId)) {
            admissionRepository.deleteById(admissionId);
            return ResponseEntity.ok("Admission with id: " + admissionId + " deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admission with id: " + admissionId + " doesn't exist.");
    }


    // Czy refactor do jednej metody?
    private ResponseEntity<String> validation(String content, Admission admission) {
        if (content.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Content cannot be empty");
        }
        admission.setContent(content);
        return ResponseEntity.ok("Validation successful");
    }

}
