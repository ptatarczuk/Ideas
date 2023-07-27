package com.example.ideas.conclusion.repository;

import com.example.ideas.conclusion.model.Conclusion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConclusionRepository extends JpaRepository<Conclusion, Long> {
}
