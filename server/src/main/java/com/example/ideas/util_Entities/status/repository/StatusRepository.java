package com.example.ideas.util_Entities.status.repository;

import com.example.ideas.util_Entities.status.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("SELECT s FROM Status s")
    Set<Status> findAllEntriesInSet();

    Optional<Status> findStatusByName(String name);

}
