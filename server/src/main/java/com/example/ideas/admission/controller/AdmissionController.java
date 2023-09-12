package com.example.ideas.admission.controller;

import com.example.ideas.admission.model.Admission;
import com.example.ideas.admission.service.AdmissionService;
import com.example.ideas.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RequestMapping("/admission")
@RestController
public class AdmissionController {

    private final AdmissionService admissionService;

    @Autowired
    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @GetMapping("/{admission_id}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable("admission_id") Long admissionId) {
        Admission admission = admissionService.getAdmissionById(admissionId).orElse(null);
        return admission != null ? ResponseEntity.ok(admission) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Admission> addAdmission(@Valid @RequestBody AdmissionCreateDTO admissionCreateDTO) throws EntityNotFoundException, IOException {
        return new ResponseEntity<>(admissionService.addAdmission(admissionCreateDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{admission_id}")
    public ResponseEntity<Admission> updateAdmissionById(
            @PathVariable("admission_id") Long admissionId,
            @RequestBody AdmissionUpdateDTO admissionUpdateDTO
    ) throws EntityNotFoundException {
        return ResponseEntity.ok(admissionService.updateAdmissionById(admissionId, admissionUpdateDTO));
    }

    @DeleteMapping("/{admission_id}")
    public ResponseEntity<String> deleteAdmissionById(@PathVariable("admission_id") Long admissionId) {
        return admissionService.deleteAdmissionById(admissionId);
    }
}
