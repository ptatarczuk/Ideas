package com.example.ideas.opinionOne.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class OpinionOne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int opinionOneId;
}
