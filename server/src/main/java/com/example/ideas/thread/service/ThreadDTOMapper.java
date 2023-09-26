package com.example.ideas.thread.service;

import com.example.ideas.admission.service.AdmissionDTOMapper;
import com.example.ideas.comment.service.CommentDTOMapper;
import com.example.ideas.conclusion.service.ConclusionDTOMapper;
import com.example.ideas.thread.controller.ThreadResponseDTO;
import com.example.ideas.thread.model.Thread;
import com.example.ideas.user.service.UserDTOMapper;
import com.example.ideas.util_Entities.vote.service.VoteDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class ThreadDTOMapper implements Function<Thread, ThreadResponseDTO> {

    private final UserDTOMapper userDTOMapper;
    private final CommentDTOMapper commentDTOMapper;
    private final AdmissionDTOMapper admissionDTOMapper;
    private final ConclusionDTOMapper conclusionDTOMapper;
    private final VoteDTOMapper voteDTOMapper;

    @Override
    public ThreadResponseDTO apply(Thread thread) {
        return new ThreadResponseDTO(
                thread.getThreadId(),
                thread.getDate(),
                thread.getTitle(),
                thread.getDescription(),
                thread.getJustification(),
                thread.getPhoto(),
                thread.getPoints(),
                userDTOMapper.apply(thread.getUser()),
                thread.getCategory(),
                thread.getStage(),
                thread.getStatus(),
                thread.getComments() != null ? thread.getComments().stream()
                        .map(commentDTOMapper).toList() :
                        null,
                thread.getVotes() != null ? thread.getVotes().stream()
                        .map(voteDTOMapper).toList() :
                        null,
                thread.getAdmission() != null ? admissionDTOMapper.apply(thread.getAdmission()) : null,
                thread.getConclusion() != null ? conclusionDTOMapper.apply(thread.getConclusion()) : null
        );
    }
}
