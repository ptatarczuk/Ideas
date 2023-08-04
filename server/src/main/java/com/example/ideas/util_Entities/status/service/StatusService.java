package com.example.ideas.util_Entities.status.service;

import com.example.ideas.util_Entities.status.model.Status;
import com.example.ideas.util_Entities.status.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Set<Status> getStatuses() {
        return statusRepository.findAllEntriesInSet();
    }

    public Optional<Status> getStatusByName(String statusName) {
        return statusRepository.findStatusByName(statusName);
    }

    public Optional<Status> getStatusById(Long statusId) {
        return statusRepository.findById(statusId);
    }

//    public ResponseEntity<String> addStatus(@Valid Status status) {
//
//        if (status.getName().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status name can't be empty");
//        }
//        statusRepository.save(new Status(status.getName()));
//        return ResponseEntity.ok("Status added successfully");
//    }
//
//    @Transactional
//    public ResponseEntity<String> updateStatusByName(String statusName, Status updatedStatus) {
//        Status searchedStatus = statusRepository.findStatusByName(statusName).orElse(null);
//        if (searchedStatus == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided Status with name:" + statusName + " does not exist");
//        }
//        searchedStatus.setName(updatedStatus.getName());
//        return ResponseEntity.ok("Status updated successfully");
//    }
//
//    @Transactional
//    public ResponseEntity<String> updateStatusById(Long statusId, Status updatedStatus) {
//        Status searchedStatus = statusRepository.findById(statusId).orElse(null);
//        if (searchedStatus == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided Status with id:" + statusId + " does not exist");
//        }
//        searchedStatus.setName(updatedStatus.getName());
//        return ResponseEntity.ok("Status updated successfully");
//    }
//
//    public ResponseEntity<String> deleteStatusById(Long statusId) {
//        if (statusRepository.existsById(statusId)) {
//            statusRepository.deleteById(statusId);
//            return ResponseEntity.ok("Status with id: " + statusId + " deleted successfully.");
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status with id: " + statusId + " doesn't exist.");
//    }
}
