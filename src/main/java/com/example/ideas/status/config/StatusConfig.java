package com.example.ideas.status.config;

import com.example.ideas.status.model.Status;
import com.example.ideas.status.repository.StatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class StatusConfig {

    @Bean
    CommandLineRunner commandLineRunnerStatus(StatusRepository repository) {
        return args -> {
            Set<Status> entriesToAdd = new HashSet<>();
            Status firstStatus = new Status("First status");
            Status secondStatus = new Status("Second status");
            entriesToAdd.add(firstStatus);
            entriesToAdd.add(secondStatus);
            repository.saveAll(entriesToAdd);
        };
    }

}
