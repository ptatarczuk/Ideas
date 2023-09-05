package com.example.ideas.thread.controller;

import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.service.ThreadService;
import com.example.ideas.thread.utils.EmailSender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/threads")
@RestController
public class ThreadController {

    private final ThreadService threadService;
    private EmailSender emailSender;

    @Autowired
    public ThreadController(ThreadService threadService, EmailSender emailSender) {
        this.threadService = threadService;
        this.emailSender = emailSender;
    }

    @GetMapping("/email")
    public boolean sendEmail() {
        String from = "emailsender666666@gmail.com";
        String recipientEmail = "emailsender666666@gmail.com";
        String subject = "Test Email";
        String text = "This is a test email sent using Spring Boot Mail.";
        emailSender.sendEmail(from, recipientEmail, subject, text);
        return true;
    }

    //    @PreAuthorize("hasRole('Admin')")
//    @PreAuthorize("hasAnyRole('Admin', 'User')")
    @GetMapping("/")
    public List<Thread> getThreads() {  // TODO: zmienic void na List<Thread>
        return threadService.getThreads();
    }

    @GetMapping("/id/{thread_id}")
    public ResponseEntity<Thread> getThreadById(
            @PathVariable("thread_id") Long threadId
    ) {
        Thread thread = threadService.getThreadById(threadId).orElse(null);
        return thread != null ? ResponseEntity.ok(thread) : ResponseEntity.notFound().build();
    }

    // czy tu beda potrzebne getByStatus getByCategory getByStage itp ?

    @PostMapping("/addThread")
    public ResponseEntity<Thread> addThread(@Valid @RequestBody Thread thread) {
        return threadService.addThread(thread);
    }

    @DeleteMapping("/{thread_id}")
    public ResponseEntity<Thread> deleteThread(@PathVariable("thread_id") Long threadId) {
        return threadService.deleteThread(threadId);
    }

    @PatchMapping("/id/{thread_id}")
    public ResponseEntity<?> updateThreadById(
            @PathVariable("thread_id") Long threadId,
            @RequestBody ThreadDTO updatedThread
    ) {
        return threadService.updateThreadById(threadId, updatedThread);
    }

    @PatchMapping("/id999/{thread_id}")
    public ResponseEntity<?> updateThreadById999(
            @PathVariable("thread_id") Long threadId,
            @RequestBody Map<String, Object> fields
    ){
        return threadService.updateThreadById999(threadId, fields);
    }
}
