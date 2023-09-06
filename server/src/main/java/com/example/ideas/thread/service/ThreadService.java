package com.example.ideas.thread.service;

import com.example.ideas.thread.controller.ThreadCreateDTO;
import com.example.ideas.thread.controller.ThreadUpdateDTO;
import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.repository.ThreadRepository;
import com.example.ideas.user.model.User;
import com.example.ideas.user.repository.UserRepository;
import com.example.ideas.util_Entities.category.model.Category;
import com.example.ideas.util_Entities.category.repository.CategoryRepository;
import com.example.ideas.util_Entities.stage.model.Stage;
import com.example.ideas.util_Entities.stage.repository.StageRepository;
import com.example.ideas.util_Entities.status.model.Status;
import com.example.ideas.util_Entities.status.repository.StatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final StageRepository stageRepository;
    private final StatusRepository statusRepository;



    public List<Thread> getThreads() {
        return threadRepository.findAll();
    }

    public Optional<Thread> getThreadById(Long id) {
        return threadRepository.findById(id);
    }

    //Walidacja?
    public ResponseEntity<?> addThread(ThreadCreateDTO threadDTO) {

        User user = userRepository.findUserByEmail(threadDTO.getUserEmail()).orElse(null);
        Category category = categoryRepository.findById(threadDTO.getCategoryId()).orElse(null);
        Stage stage = stageRepository.findById(1L).orElse(null);
        Status status = statusRepository.findById(1L).orElse(null);

        if(user == null || category == null || stage == null || status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("given user or category does not exist in database");
        }

        Thread thread = Thread.builder()
                .date(LocalDate.now())
                .title(threadDTO.getTitle())
                .description(threadDTO.getDescription())
                .justification(threadDTO.getJustification())
                .user(user)
                .category(category)
                .stage(stage)
                .status(status)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(threadRepository.save(thread));
    }

    public ResponseEntity<Thread> deleteThread(Long id) {
        Optional<Thread> threadOptional = threadRepository.findById(id);
        if (threadOptional.isPresent()) {
            threadRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(threadOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Walidacja?
    @Transactional
    public ResponseEntity<?> updateThreadById(Long threadId, ThreadUpdateDTO threadUpdateDTO) {
        Thread thread = threadRepository.findById(threadId).orElse(null);
        if (thread == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided Thread with id: " + threadId + " does not exist");
        }
        if (threadUpdateDTO.getEmail() != null && !thread.getUser().getEmail().equals(threadUpdateDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User: " + threadUpdateDTO.getEmail() + " is not authorized to modify this thread");
        }

        if (threadUpdateDTO.getTitle() != null) {
            thread.setTitle(threadUpdateDTO.getTitle());
        }
        if (threadUpdateDTO.getDescription() != null) {
            thread.setDescription(threadUpdateDTO.getDescription());
        }
        if (threadUpdateDTO.getJustification() != null) {
            thread.setJustification(threadUpdateDTO.getJustification());
        }
        if (threadUpdateDTO.getPhoto() != null) {
            thread.setPhoto(threadUpdateDTO.getPhoto());
        }
        if (threadUpdateDTO.getPoints() != null) {
            thread.setPoints(threadUpdateDTO.getPoints());
        }
        if (threadUpdateDTO.getCategory() != null) {
            thread.setCategory(threadUpdateDTO.getCategory());
        }
        if (threadUpdateDTO.getStage() != null) {
            thread.setStage(threadUpdateDTO.getStage());
        }
        if (threadUpdateDTO.getStatus() != null) {
            thread.setStatus(threadUpdateDTO.getStatus());
        }
        if (threadUpdateDTO.getAdmission() != null) {
            thread.setAdmission(threadUpdateDTO.getAdmission());
        }
        if (threadUpdateDTO.getConclusion() != null) {
            thread.setConclusion(threadUpdateDTO.getConclusion());
    }

    // czy tutaj updatujemy admission i conclusion ? one maja swoje update'y
        return ResponseEntity.ok(thread);
    }

    public ResponseEntity<?> updateThreadById999(Long threadId, Map<String, Object> fields) {
        Thread thread = threadRepository.findById(threadId).orElse(null);
        if (thread == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided Thread with id:" + threadId + " does not exist");
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Thread.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, thread, value);
            }
        });
        return ResponseEntity.ok(threadRepository.save(thread));
    }
}