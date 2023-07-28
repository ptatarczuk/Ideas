//package com.example.ideas.thread.config;
//
//import com.example.ideas.admission.repository.AdmissionRepository;
//import com.example.ideas.category.repository.CategoryRepository;
//import com.example.ideas.conclusion.repository.ConclusionRepository;
//import com.example.ideas.stage.repository.StageRepository;
//import com.example.ideas.status.repository.StatusRepository;
//import com.example.ideas.thread.model.Thread;
//import com.example.ideas.thread.repository.ThreadRepository;
//import com.example.ideas.user.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class ThreadConfig {
//
//    private final UserRepository userRepository;
//    private final CategoryRepository categoryRepository;
//    private final StageRepository stageRepository;
//
//    private final AdmissionRepository admissionRepository;
//
//    private final StatusRepository statusRepository;
//
//    private final ConclusionRepository conclusionRepository;
//
//    @Autowired
//    public ThreadConfig(UserRepository userRepository, CategoryRepository categoryRepository,
//                        StageRepository stageRepository, AdmissionRepository admissionRepository,
//                        StatusRepository statusRepository, ConclusionRepository conclusionRepository) {
//        this.userRepository = userRepository;
//        this.categoryRepository = categoryRepository;
//        this.stageRepository = stageRepository;
//        this.admissionRepository = admissionRepository;
//        this.statusRepository = statusRepository;
//        this.conclusionRepository = conclusionRepository;
//    }
//
//    @Bean
//    CommandLineRunner commandLineRunnerThread(ThreadRepository repository) {
//        return args -> {
//            Thread thread1 = new Thread("Fruits on monday", "description", "justification", "photo", 100,
//                    userRepository.findById(1L).get(), categoryRepository.findById(1L).get(), stageRepository.findById(1L).get(),
//                    admissionRepository.findById(1L).get(), statusRepository.findById(1L).get(), conclusionRepository.findById(1L).get());
//            Thread thread2 = new Thread("No trousers on Fridays", "description2", "justification2", "photo2", 50,
//                    userRepository.findById(2L).get(), categoryRepository.findById(2L).get(), stageRepository.findById(2L).get(),
//                    admissionRepository.findById(2L).get(), statusRepository.findById(2L).get(), conclusionRepository.findById(2L).get());
//            repository.saveAll(List.of(thread1, thread2));
//        };
//    }
//}
