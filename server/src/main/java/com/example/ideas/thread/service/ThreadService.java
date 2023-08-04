package com.example.ideas.thread.service;

import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.repository.ThreadRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThreadService {

    private ThreadRepository threadRepository;

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
        threadRepository.save(thread);
        return ResponseEntity.status(HttpStatus.OK).body(thread);
    }

    public ResponseEntity<Thread> deleteThread(Long id) {
        Optional<Thread> threadOptional = threadRepository.findById(id);
        if(threadOptional.isPresent()) {
            threadRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(threadOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Walidacja?
    @Transactional
    public ResponseEntity<String> updateThreadById(Long threadId, Thread updatedThread) {
        Thread thread = threadRepository.findById(threadId).orElse(null);
        if (thread == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided Thread with id:" + threadId + " does not exist");
        }
        thread.setTitle(updatedThread.getTitle());
        thread.setDescription(updatedThread.getDescription());
        thread.setJustification(updatedThread.getJustification());
        thread.setPhoto(updatedThread.getPhoto());
        thread.setPoints(updatedThread.getPoints());
        thread.setUser(updatedThread.getUser());
        thread.setCategory(updatedThread.getCategory());
        thread.setStage(updatedThread.getStage());
        thread.setStatus(updatedThread.getStatus());
        // czy tutaj updatujemy admission i conclusion ? one maja swoje update'y
        thread.setAdmission(updatedThread.getAdmission());
        thread.setConclusion(updatedThread.getConclusion());
        return ResponseEntity.ok("Thread updated successfully");
    }

}

