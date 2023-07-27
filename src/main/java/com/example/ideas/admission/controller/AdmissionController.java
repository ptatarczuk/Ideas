package com.example.ideas.admission.controller;

import com.example.ideas.admission.model.Admission;
import com.example.ideas.admission.service.AdmissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> addAdmission(@Valid @RequestBody Admission admission) {
        return admissionService.addAdmission(admission);

    }

    @PutMapping("/{admission_id}")
    public ResponseEntity<String> updateAdmissionById(
            @PathVariable("admission_id") Long admissionId,
            @RequestBody Admission updatedAdmission
    ) {
        return admissionService.updateAdmissionById(admissionId, updatedAdmission);
    }

    @DeleteMapping("/{admission_id}")
    public ResponseEntity<String> deleteAdmissionById(@PathVariable("admission_id") Long admissionId) {
        return admissionService.deleteAdmissionById(admissionId);
    }
}
