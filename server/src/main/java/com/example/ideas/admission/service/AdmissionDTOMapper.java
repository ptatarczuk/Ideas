package com.example.ideas.admission.service;

import com.example.ideas.admission.controller.AdmissionResponseDTO;
import com.example.ideas.admission.model.Admission;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AdmissionDTOMapper implements Function<Admission, AdmissionResponseDTO> {
    @Override
    public AdmissionResponseDTO apply(Admission admission) {
        return new AdmissionResponseDTO(
                admission.getAdmissionId(),
                admission.getContent(),
                admission.getDateOfPost(),
                admission.getUser().getUserId(),
                admission.getThread().getThreadId()
        );
    }
}
