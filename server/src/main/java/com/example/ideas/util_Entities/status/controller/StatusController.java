package com.example.ideas.util_Entities.status.controller;

import com.example.ideas.util_Entities.status.model.Status;
import com.example.ideas.util_Entities.status.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/status")
@RestController
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/")
    public Set<Status> getStatuses(){
        return statusService.getStatuses();
    }

    @GetMapping("/name/{status_name}")
    public ResponseEntity<Status> getStatusByName(@PathVariable("status_name") String statusName) {
        Status status = statusService.getStatusByName(statusName).orElse(null);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{status_id}")
    public ResponseEntity<Status> getStatusById(@PathVariable("status_id") Long statusId) {
        Status status = statusService.getStatusById(statusId).orElse(null);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }

//    @PostMapping("/addStatus")
//    public ResponseEntity<String> addStatus(@Valid @RequestBody Status status) {
//        return statusService.addStatus(status);
//    }
//
//    @PutMapping("/name/{status_name}")
//    public ResponseEntity<String> updateStatusByName(
//            @PathVariable("status_name") String statusName,
//            @RequestBody Status updatedStatus
//    ) {
//        return statusService.updateStatusByName(statusName, updatedStatus );
//    }
//
//    @PutMapping("/id/{status_id}")
//    public ResponseEntity<String> updateStatusById(
//            @PathVariable("status_id") Long statusId,
//            @RequestBody Status updatedStatus
//    ) {
//        return statusService.updateStatusById(statusId, updatedStatus );
//    }
//
//    @DeleteMapping("/{status_id}")
//    public ResponseEntity<String> deleteStatusById(@PathVariable("status_id") Long statusId) {
//        return statusService.deleteStatusById(statusId);
//    }

}
