package com.example.ideas.conclusion.service;

import com.example.ideas.admission.controller.AdmissionResponseDTO;
import com.example.ideas.admission.model.Admission;
import com.example.ideas.conclusion.controller.ConclusionCreateDTO;
import com.example.ideas.conclusion.controller.ConclusionResponseDTO;
import com.example.ideas.conclusion.controller.ConclusionUpdateDTO;
import com.example.ideas.conclusion.model.Conclusion;
import com.example.ideas.conclusion.repository.ConclusionRepository;
import com.example.ideas.exception.DataAlreadyExistsException;
import com.example.ideas.exception.EntityNotFoundException;
import com.example.ideas.helpers.ObjectProvider;
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

import static com.example.ideas.helpers.ObjectProvider.getObjectFromDB;

@Service
@RequiredArgsConstructor
public class ConclusionService {

    private final ConclusionRepository conclusionRepository;
    private final UserRepository userRepository;
    private final ThreadRepository threadRepository;
    private final StageRepository stageRepository;
    private final StatusRepository statusRepository;
    private final ConclusionDTOMapper conclusionDTOMapper;


    public ConclusionResponseDTO getConclusionById(Long conclusionId) throws EntityNotFoundException {
        Conclusion conclusion = ObjectProvider.getObjectFromDB(conclusionId, conclusionRepository);
        return conclusionDTOMapper.apply(conclusion);
    }

    public ConclusionResponseDTO addConclusion(ConclusionCreateDTO conclusionDTO) throws EntityNotFoundException, DataAlreadyExistsException {

        Thread thread = getObjectFromDB(conclusionDTO.getThreadId(), threadRepository);
        if(thread.getConclusion() != null) {
            throw new DataAlreadyExistsException("Given thread already has conclusion");
        }
        User user = getObjectFromDB(conclusionDTO.getUserId(), userRepository);

        Conclusion conclusion = Conclusion.builder()
                .dateOfPost(LocalDate.now())
                .content(conclusionDTO.getContent())
                .thread(thread)
                .user(user)
                .build();

        Stage stage = getObjectFromDB(conclusionDTO.getStageId(), stageRepository);
        Status status = getObjectFromDB(2L, statusRepository);

        thread.setStage(stage);
        thread.setStatus(status);
        threadRepository.save(thread);

        return conclusionDTOMapper.apply(conclusionRepository.save(conclusion));
    }


    @Transactional
    public ConclusionResponseDTO updateConclusionById(Long conclusionId, ConclusionUpdateDTO conclusionDTO)
            throws EntityNotFoundException {

        Conclusion conclusion = getObjectFromDB(conclusionId, conclusionRepository);
        conclusion.setDateOfPost(LocalDate.now());

        if (conclusionDTO.getContent() != null) {
            conclusion.setContent(conclusionDTO.getContent());
        }
        if (conclusionDTO.getUserId() != null) {
            User user = getObjectFromDB(conclusionDTO.getUserId(), userRepository);
            conclusion.setUser(user);
        }
        if (conclusionDTO.getStageId() != null) {
            Thread thread = getObjectFromDB(conclusionId, threadRepository);
            Stage stage = getObjectFromDB(conclusionDTO.getStageId(), stageRepository);
            thread.setStage(stage);
            Status status = getObjectFromDB(
                    conclusionDTO.getStageId() == 5 ? 2L : 1L
                    , statusRepository
            );
            thread.setStatus(status);
        }
        return conclusionDTOMapper.apply(conclusion);
    }

    public ResponseEntity<String> deleteConclusionById(Long conclusionId) {
        if (conclusionRepository.existsById(conclusionId)) {
            conclusionRepository.deleteById(conclusionId);
            return ResponseEntity.ok("Conclusion with id: " + conclusionId + " deleted successfully.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conclusion with id: " + conclusionId + " doesn't exist.");
    }


}
