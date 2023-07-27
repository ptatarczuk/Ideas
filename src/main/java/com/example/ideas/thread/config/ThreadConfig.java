package com.example.ideas.thread.config;

import com.example.ideas.category.repository.CategoryRepository;
import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.repository.ThreadRepository;
import com.example.ideas.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
//public class ThreadConfig {
//
//    private final UserRepository userRepository;
////    private final CategoryRepository categoryRepository;
//
//    @Autowired
//    public ThreadConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Bean
//    CommandLineRunner commandLineRunnerThread(ThreadRepository repository) {
//        return args -> {
//            Thread thread1 = new Thread("Owocowy poniedziałek", 10, userRepository.findById(1L).get());
//            Thread thread2 = new Thread("Owocowy wtorek", 10, userRepository.findById(2L).get());
//            Thread thread3 = new Thread("Owocowy czwartek", 10, userRepository.findById(3L).get());
//
//            repository.saveAll(List.of(thread1, thread2, thread3));
//        };
//    }
//}
