package com.example.ideas.util_Entities.stage.service;

import com.example.ideas.util_Entities.stage.model.Stage;
import com.example.ideas.util_Entities.stage.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    private final StageRepository stageRepository;

    @Autowired
    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public List<Stage> getStages() {
        return stageRepository.findAll();
    }

    public Optional<Stage> getStageById(Long stageId) {
        return stageRepository.findById(stageId);
    }

    public Optional<Stage> getStageByName(String stageName) {
        return stageRepository.findStageByStageName(stageName);
    }
}
