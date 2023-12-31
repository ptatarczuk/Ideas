package com.example.ideas.thread.controller;

import com.example.ideas.exception.EntityNotFoundException;
import com.example.ideas.exception.NoAuthorizationException;
import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.service.ThreadService;
import com.example.ideas.thread.utils.EmailSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/threads")
@RestController
public class ThreadController {

    private final ThreadService threadService;
    private EmailSender emailSender;

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
    public Map<String, Object> getThreads(
            @RequestParam Integer pageNo,
            @RequestParam Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String searchedTitle,
            @RequestParam(required = false, defaultValue = "1") Long filterStatusId
    ) throws EntityNotFoundException {
        return threadService.getThreads(pageNo, pageSize, searchedTitle, filterStatusId);
    }

    @GetMapping("/id/{thread_id}")
    public ResponseEntity<Thread> getThreadById(
            @PathVariable("thread_id") Long threadId
    ) {
        Thread thread = threadService.getThreadById(threadId).orElse(null);
        return thread != null ? ResponseEntity.ok(thread) : ResponseEntity.notFound().build();
    }

    // czy tu beda potrzebne getByStatus getByCategory getByStage itp ?

//    @RequestMapping(path = "/addThread", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<Thread> addThread(
//            @RequestPart @Valid ThreadCreateDTO threadCreateDTO,
//            @RequestPart(value = "file", required = false) MultipartFile multipartFile
//    ) throws EntityNotFoundException, IOException {
//        return new ResponseEntity<>(threadService.addThread(multipartFile, threadCreateDTO), HttpStatus.CREATED);
//    }

    @RequestMapping(path = "/addThread", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Thread> addThread(
            @RequestParam("model") String model,
            @RequestParam(value = "file", required = false) MultipartFile multipartFile
    ) throws EntityNotFoundException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        return new ResponseEntity<>(threadService.addThread(
                multipartFile,
                mapper.readValue(model, ThreadCreateDTO.class)),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{thread_id}")
    public ResponseEntity<Thread> deleteThread(@PathVariable("thread_id") Long threadId) {
        return threadService.deleteThread(threadId);
    }

    @PatchMapping(value = "/id/{thread_id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Thread> updateThreadById(
            @RequestHeader("Authorization") String token,
            @PathVariable("thread_id") Long threadId,
            @RequestParam(value ="file", required = false) MultipartFile multipartFile,
            @RequestParam(value = "thread") String model
    ) throws IOException, EntityNotFoundException, NoAuthorizationException {
        ObjectMapper mapper = new ObjectMapper();
        return new  ResponseEntity<>(threadService.updateThreadById(
                token,
                threadId,
                multipartFile,
                mapper.readValue(model, ThreadUpdateDTO.class)
        ), HttpStatus.OK);
    }

}
