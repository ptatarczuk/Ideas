package com.example.ideas.util_Entities.vote.service;

import com.example.ideas.util_Entities.vote.controller.VoteResponseDTO;
import com.example.ideas.util_Entities.vote.model.Vote;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class VoteDTOMapper implements Function<Vote, VoteResponseDTO> {
    @Override
    public VoteResponseDTO apply(Vote vote) {
        return new VoteResponseDTO(
                vote.getId(),
                vote.getThread().getThreadId(),
                vote.getUser().getUserId(),
                vote.getVoteType()
        );
    }
}
