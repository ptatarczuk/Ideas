package com.example.ideas.util_Entities.vote.controller;

import com.example.ideas.util_Entities.vote.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/votes")
@RestController
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/addVote")
    public ResponseEntity<String> addVote(
            @RequestBody VoteRequest voteRequest
    ) {
        return voteService.addVote(voteRequest.getThreadId(), voteRequest.getUserId(), voteRequest.getVoteType());
    }

    @DeleteMapping("/{vote_id}")
    public ResponseEntity<String> deleteVote(
            @PathVariable ("vote_id") Long voteId,
            @RequestBody Long userId
    ) {
        return voteService.deleteVote(voteId, userId);
    }
}
