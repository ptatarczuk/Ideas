package com.example.ideas.helpers;

import com.example.ideas.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public class ObjectProvider {

    public static <T, V extends JpaRepository<T, Long>> T getObjectFromDB(Long objectId, V repository)
            throws EntityNotFoundException {
        return repository.findById(objectId)
                .orElseThrow(() -> new EntityNotFoundException("Object: " + objectId + " does not exist in database"));
    }
}
