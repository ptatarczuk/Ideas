package com.example.ideas.status.repository;

import com.example.ideas.status.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("SELECT s FROM Status s")
    Set<Status> findAllEntriesInSet();

    Optional<Status> findStatusByName(String name);

}
