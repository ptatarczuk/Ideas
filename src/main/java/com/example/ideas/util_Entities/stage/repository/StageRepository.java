package com.example.ideas.util_Entities.stage.repository;

import com.example.ideas.util_Entities.stage.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

    Optional<Stage> findStageByStageName(String stageName);
}
