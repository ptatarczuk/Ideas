package com.example.ideas.admission.service;

import com.example.ideas.admission.controller.AdmissionCreateDTO;
import com.example.ideas.admission.controller.AdmissionUpdateDTO;
import com.example.ideas.admission.model.Admission;
import com.example.ideas.admission.repository.AdmissionRepository;
import com.example.ideas.exception.EntityNotFoundException;
import com.example.ideas.exception.DataAlreadyExistsException;
import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.repository.ThreadRepository;
import com.example.ideas.user.model.User;
import com.example.ideas.user.repository.UserRepository;
import com.example.ideas.util_Entities.stage.model.Stage;
import com.example.ideas.util_Entities.stage.repository.StageRepository;
import com.example.ideas.util_Entities.status.model.Status;
import com.example.ideas.util_Entities.status.repository.StatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.ideas.thread.utils.ObjectProvider.getObjectFromDB;

@Service
@RequiredArgsConstructor
public class AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final UserRepository userRepository;
    private final ThreadRepository threadRepository;
    private final StageRepository stageRepository;
    private final StatusRepository statusRepository;

    public Optional<Admission> getAdmissionById(Long admissionId) {
        return admissionRepository.findById(admissionId);
    }

    public Admission addAdmission(AdmissionCreateDTO admissionDTO) throws EntityNotFoundException, DataAlreadyExistsException {

        Thread thread = getObjectFromDB(admissionDTO.getThreadId(), threadRepository);
        if(thread.getAdmission() != null) {
            throw new DataAlreadyExistsException("Given thread already has admission");
        }
        User user = getObjectFromDB(admissionDTO.getUserId(), userRepository);

        Admission admission = Admission.builder()
                .dateOfPost(LocalDate.now())
                .content(admissionDTO.getContent())
                .thread(thread)
                .user(user)
                .build();

        Stage stage = getObjectFromDB(admissionDTO.getStageId(), stageRepository);
        Status status = getObjectFromDB(admissionDTO.getStageId() == 3 ? 2L : 1L, statusRepository);

        thread.setStage(stage);
        thread.setStatus(status);
        threadRepository.save(thread);

        return admissionRepository.save(admission);
    }

    @Transactional
    public Admission updateAdmissionById(Long admissionId, AdmissionUpdateDTO admissionDTO)
            throws EntityNotFoundException {

        Admission admission = getObjectFromDB(admissionId, admissionRepository);
        admission.setDateOfPost(LocalDate.now());

        if (admissionDTO.getContent() != null) {
            admission.setContent(admissionDTO.getContent());
        }
        if (admissionDTO.getUserId() != null) {
            User user = getObjectFromDB(admissionDTO.getUserId(), userRepository);
            admission.setUser(user);
        }
        if (admissionDTO.getStageId() != null) {
            Thread thread = getObjectFromDB(admissionId, threadRepository);
            Stage stage = getObjectFromDB(admissionDTO.getStageId(), stageRepository);
            thread.setStage(stage);
            Status status = getObjectFromDB(
                    admissionDTO.getStageId() == 3 ? 2L : 1L
                    , statusRepository
            );
            thread.setStatus(status);
        }
        return admission;
    }

    public ResponseEntity<String> deleteAdmissionById(Long admissionId) {
        if (admissionRepository.existsById(admissionId)) {
            admissionRepository.deleteById(admissionId);
            return ResponseEntity.ok("Admission with id: " + admissionId + " deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admission with id: " + admissionId + " doesn't exist.");
    }

}
