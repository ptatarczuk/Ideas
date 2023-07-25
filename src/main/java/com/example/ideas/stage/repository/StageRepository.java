package com.example.ideas.stage.repository;

import com.example.ideas.stage.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

    Optional<Stage> findStageByStageName(String stageName);
}
