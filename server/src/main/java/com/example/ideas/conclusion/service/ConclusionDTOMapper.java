package com.example.ideas.conclusion.service;

import com.example.ideas.conclusion.controller.ConclusionResponseDTO;
import com.example.ideas.conclusion.model.Conclusion;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ConclusionDTOMapper implements Function<Conclusion, ConclusionResponseDTO> {
    @Override
    public ConclusionResponseDTO apply(Conclusion conclusion) {
        return new ConclusionResponseDTO(
                conclusion.getConclusionId(),
                conclusion.getContent(),
                conclusion.getDateOfPost(),
                conclusion.getUser().getUserId(),
                conclusion.getThread().getThreadId()
        );
    }
}
