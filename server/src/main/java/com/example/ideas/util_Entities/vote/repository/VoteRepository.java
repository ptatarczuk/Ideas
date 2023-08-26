package com.example.ideas.util_Entities.vote.repository;

import com.example.ideas.util_Entities.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

//    List<Vote> findAllByThread_ThreadIdIsLikeAndUserIsLike(Long threadId, Long userId);


    @Query("SELECT v FROM Vote v " +
            "WHERE v.thread.threadId = :threadId AND v.user.userId = :userId")
    List<Vote> findVotesByThreadAndUser(@Param("threadId") Long threadId, @Param("userId") Long userId);
}
