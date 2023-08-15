package com.example.ideas.thread.repository;

import com.example.ideas.thread.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long>{
}