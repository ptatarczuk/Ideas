package com.example.ideas.util_Entities.vote.repository;

import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.repository.ThreadRepository;
import com.example.ideas.user.model.User;
import com.example.ideas.user.repository.UserRepository;
import com.example.ideas.util_Entities.vote.model.Vote;
import com.example.ideas.util_Entities.vote.model.VoteType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class VoteRepositoryTest {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    private boolean addVote(Long threadId, Long userId, VoteType voteType) {
        Optional<Thread> threadOptional = threadRepository.findById(threadId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(threadOptional.isEmpty() || userOptional.isEmpty()) {
            return false;
        }
        Vote vote = Vote.builder()
                .thread(threadOptional.get())
                .user(userOptional.get())
                .voteType(voteType)
                .build();

        underTest.save(vote);
        return true;
    }

    @Test
    void itShouldFindTwoVotesByThreadAndUser() {
        //given
        addVote(1L, 1L, VoteType.LIKE);
        addVote(1L, 1L, VoteType.DISLIKE);
        int expected = 2;
        //when
        List<Vote> result = underTest.findVotesByThreadAndUser(1L, 1L);
        //then
        Assertions.assertThat(expected).isEqualTo(result.size());
    }
}