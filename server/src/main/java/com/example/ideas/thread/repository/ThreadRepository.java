package com.example.ideas.thread.repository;

import com.example.ideas.thread.model.Thread;
import com.example.ideas.util_Entities.status.model.Status;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

    Page<Thread> findByTitleContainsIgnoreCaseAndStatus(String searchedTitle, Status status, Pageable pageable);
    Page<Thread> findByStatus(Status status, Pageable pageable);

}
