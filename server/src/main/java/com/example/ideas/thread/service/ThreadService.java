package com.example.ideas.thread.service;

import com.example.ideas.thread.controller.ThreadDTO;
import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.repository.ThreadRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ThreadService {

    private final ThreadRepository threadRepository;

    @Autowired
    public ThreadService(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    public List<Thread> getThreads() {
        return threadRepository.findAll();
    }

    public Optional<Thread> getThreadById(Long id) {
        return threadRepository.findById(id);
    }

    //Walidacja?
    public ResponseEntity<Thread> addThread(@Valid Thread thread) {
        thread.setDate(LocalDate.now());
        threadRepository.save(thread);
        return ResponseEntity.status(HttpStatus.OK).body(thread);
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
    public ResponseEntity<?> updateThreadById(Long threadId, ThreadDTO threadDTO) {
        Thread thread = threadRepository.findById(threadId).orElse(null);
        if (thread == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided Thread with id: " + threadId + " does not exist");
        }
        if (threadDTO.getEmail() != null && !thread.getUser().getEmail().equals(threadDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User: " + threadDTO.getEmail() + " is not authorized to modify this thread");
        }

        if (threadDTO.getTitle() != null) {
            thread.setTitle(threadDTO.getTitle());
        }
        if (threadDTO.getDescription() != null) {
            thread.setDescription(threadDTO.getDescription());
        }
        if (threadDTO.getJustification() != null) {
            thread.setJustification(threadDTO.getJustification());
        }
        if (threadDTO.getPhoto() != null) {
            thread.setPhoto(threadDTO.getPhoto());
        }
        if (threadDTO.getPoints() != null) {
            thread.setPoints(threadDTO.getPoints());
        }
        if (threadDTO.getCategory() != null) {
            thread.setCategory(threadDTO.getCategory());
        }
        if (threadDTO.getStage() != null) {
            thread.setStage(threadDTO.getStage());
        }
        if (threadDTO.getStatus() != null) {
            thread.setStatus(threadDTO.getStatus());
        }
        if (threadDTO.getAdmission() != null) {
            thread.setAdmission(threadDTO.getAdmission());
        }
        if (threadDTO.getConclusion() != null) {
            thread.setConclusion(threadDTO.getConclusion());
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