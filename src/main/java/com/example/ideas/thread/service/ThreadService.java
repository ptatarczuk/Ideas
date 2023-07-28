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
        Thread searchedThread = threadRepository.findById(threadId).orElse(null);
        if (searchedThread == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided Thread with id:" + threadId + " does not exist");
        }
        searchedThread.setTitle(updatedThread.getTitle());
        searchedThread.setDescription(updatedThread.getDescription());
        searchedThread.setJustification(updatedThread.getJustification());
        searchedThread.setPhoto(updatedThread.getPhoto());
        searchedThread.setPoints(updatedThread.getPoints());
        searchedThread.setUser(updatedThread.getUser());
        searchedThread.setCategory(updatedThread.getCategory());
        searchedThread.setStage(updatedThread.getStage());
        searchedThread.setAdmission(updatedThread.getAdmission());
        searchedThread.setStatus(updatedThread.getStatus());
        searchedThread.setConclusion(updatedThread.getConclusion());
        return ResponseEntity.ok("Thread updated successfully");
    }

}
