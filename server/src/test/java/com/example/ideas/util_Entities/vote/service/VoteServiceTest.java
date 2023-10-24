package com.example.ideas.util_Entities.vote.service;

import com.example.ideas.thread.repository.ThreadRepository;
import com.example.ideas.user.repository.UserRepository;
import com.example.ideas.util_Entities.vote.model.Vote;
import com.example.ideas.util_Entities.vote.model.VoteType;
import com.example.ideas.util_Entities.vote.repository.VoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ThreadRepository threadRepository;
    @InjectMocks
    private VoteService underTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addVote() {
        //given
        given(voteRepository.findVotesByThreadAndUser(anyLong(), anyLong()))
                .willReturn(new ArrayList<>());

        //when
        underTest.addVote(1L, 1L, VoteType.LIKE);

        //then
        verify(voteRepository, never()).deleteAll(any());



    }

    @Test
    @Disabled
    void deleteVote() {
    }
}