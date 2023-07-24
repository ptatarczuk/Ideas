package com.example.ideas.opinionTwo.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class OpinionTwo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int opinionTwoId;
}
