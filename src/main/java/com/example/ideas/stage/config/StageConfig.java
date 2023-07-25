package com.example.ideas.stage.config;

import com.example.ideas.stage.model.Stage;
import com.example.ideas.stage.repository.StageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StageConfig {
    @Bean
    CommandLineRunner commandLineRunnerStage(StageRepository repository) {
        return args -> {
            Stage s1 = new Stage(1L, "Submitted");
            Stage s2 = new Stage(2L, "Voting");
            Stage s3 = new Stage(3L, "Rejected");
            Stage s4 = new Stage(4L, "Approved");
            Stage s5 = new Stage(5L, "NotApproved");

            repository.saveAll(List.of(s1,s2,s3,s4,s5));
        };
    }



}
