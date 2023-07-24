package com.example.ideas.thread.controller;

import com.example.ideas.thread.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/threads")
@RestController
public class ThreadController {

    private ThreadService threadService;

    @Autowired
    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/")
    public void getThreads() {  // TODO: zmienic void na List<Thread>

    }

    @GetMapping("/{thread_id}")
    public void getThreadById() {

    }

    // czy tu beda potrzebne getByStatus getByCategory getByStage itp ?

    @PostMapping("/addThread")
    public void addThread() {

    }

    @PutMapping("/update_thread/{thread_id}")
    public void updateThreadById() {

    }

    @DeleteMapping("/{thread_id}")
    public void deleteThreadById() {

    }

}
