package com.example.ideas.thread.service;

import com.example.ideas.thread.repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addThread(String title, Long userId) {
        User user = userRepository.getUserById(userId)

        Thread thread = new Thread(title, user);
        threadRepository.save(thread);
    }

}
