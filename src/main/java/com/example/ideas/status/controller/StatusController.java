package com.example.ideas.status.controller;

import com.example.ideas.status.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/statuses")
@RestController
public class StatusController {

    private StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/")
    public void getStatuses(){

    }

    @GetMapping("/name/{status_name}")
    public void getStatusByName() {

    }

    @GetMapping("/id/{status_id}")
    public void getStatusById() {

    }
}
