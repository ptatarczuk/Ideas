package com.example.ideas.util_Entities.vote.controller;

import com.example.ideas.thread.controller.ThreadResponseDTO;
import com.example.ideas.util_Entities.vote.model.VoteType;

public record VoteResponseDTO(
        Long id,
        Long threadId,
        Long userId,
        VoteType voteType
) {
}
