package com.example.ideas.util_Entities.vote.service;

import com.example.ideas.thread.model.Thread;
import com.example.ideas.util_Entities.vote.model.Vote;
import com.example.ideas.util_Entities.vote.model.VoteType;
import com.example.ideas.thread.repository.ThreadRepository;
import com.example.ideas.util_Entities.vote.repository.VoteRepository;
import com.example.ideas.user.model.User;
import com.example.ideas.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final ThreadRepository threadRepository;

    public ResponseEntity<String> addVote(Long threadId, Long userId, VoteType voteType) {

        List<Vote> allByThreadAndUser = voteRepository.findVotesByThreadAndUser(threadId, userId);

        if (!allByThreadAndUser.isEmpty()) {
            voteRepository.deleteAll(allByThreadAndUser);
        }

        Optional<Thread> threadOptional = threadRepository.findById(threadId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(threadOptional.isEmpty() || userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("thread or user not found in database");
        }

        Vote vote = Vote.builder()
                .thread(threadOptional.get())
                .user(userOptional.get())
                .voteType(voteType)
                .build();

        voteRepository.save(vote);

        return ResponseEntity.ok("vote added");
    }

    public ResponseEntity<String> deleteVote(Long voteId, Long userId) {

        Optional<Vote> voteOptional = voteRepository.findById(voteId);
        if(voteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("vote not in database");
        }

        Vote vote = voteOptional.get();
        if(!vote.getUser().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("user can only delete his own votes");
        }

        voteRepository.delete(vote);

        return ResponseEntity.ok("vote deleted");
    }
}
