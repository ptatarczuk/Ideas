package com.example.ideas.util_Entities.vote.controller;

import com.example.ideas.util_Entities.vote.model.VoteType;
import lombok.Getter;

@Getter
public class VoteRequest {
        private Long threadId;
        private Long userId;
        private VoteType voteType;
}
