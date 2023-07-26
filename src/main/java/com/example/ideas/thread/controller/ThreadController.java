package com.example.ideas.thread.controller;

import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.service.ThreadService;
import com.example.ideas.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/threads")
@RestController
public class ThreadController {

    private ThreadService threadService;

    @Autowired
    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/")
    public List<Thread> getThreads() {  // TODO: zmienic void na List<Thread>
        return threadService.getThreads();
    }

    @GetMapping("/id/{thread_id}")
    public ResponseEntity<Thread> getThreadById(
            @PathVariable("thread_id") Long id
    )
    {
        Thread thread = threadService.getThreadById(id).orElse(null);
        return thread != null ? ResponseEntity.ok(thread) : ResponseEntity.notFound().build();
    }

    // czy tu beda potrzebne getByStatus getByCategory getByStage itp ?

    @PostMapping("/addThread")
    public ResponseEntity<Thread> addThread(@RequestBody Thread thread) {
        return threadService.addThread(thread);
    }

    @PutMapping("/update_thread/{thread_id}")
    public void updateThreadById() {

    }

    @DeleteMapping("/{thread_id}")
    public void deleteThreadById() {

    }

}
