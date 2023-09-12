package com.example.ideas.thread.utils;

import com.example.ideas.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class ObjectProvider {

    public static <T, V extends JpaRepository<T, Long>> T getObjectFromDB(Long objectId, V repository)
            throws EntityNotFoundException {
        return repository.findById(objectId)
                .orElseThrow(() -> new EntityNotFoundException("Object: " + objectId + " does not exist in database"));
    }
}
