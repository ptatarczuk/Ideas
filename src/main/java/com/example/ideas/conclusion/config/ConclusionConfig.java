package com.example.ideas.conclusion.config;

import com.example.ideas.conclusion.model.Conclusion;
import com.example.ideas.conclusion.repository.ConclusionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConclusionConfig {
    @Bean
    CommandLineRunner commandLineRunnerConclusion(ConclusionRepository repository) {
        return args -> {
            Conclusion firstConclusion = new Conclusion("This is awesome! I hope this idea gets a lot of supportive votes!", 80);
            Conclusion secondConclusion = new Conclusion("Mate, you high? The Board will never agree to this...", 0);
            repository.saveAll(List.of(firstConclusion, secondConclusion));
        };
    }
}
