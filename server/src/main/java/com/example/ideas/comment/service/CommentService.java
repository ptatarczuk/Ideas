package com.example.ideas.comment.service;

import com.example.ideas.comment.controller.CommentCreateDTO;
import com.example.ideas.comment.controller.CommentResponseDTO;
import com.example.ideas.comment.model.Comment;
import com.example.ideas.comment.repository.CommentRepository;
import com.example.ideas.exception.EntityNotFoundException;
import com.example.ideas.exception.NoAuthorizationException;
import com.example.ideas.helpers.ObjectProvider;
import com.example.ideas.security.config.JwtService;
import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.repository.ThreadRepository;
import com.example.ideas.user.model.User;
import com.example.ideas.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.ideas.helpers.ObjectProvider.getObjectFromDB;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ThreadRepository threadRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CommentDTOMapper commentDTOMapper;

    public List<CommentResponseDTO> getComments() {
        return commentRepository.findAll().stream().map(commentDTOMapper).toList();
    }

    public CommentResponseDTO findCommentByCommentId(Long commentId) throws EntityNotFoundException {
        Comment comment = ObjectProvider.getObjectFromDB(commentId, commentRepository);
        return commentDTOMapper.apply(comment);
    }

    public List<CommentResponseDTO> findCommentsByUserId(Long userId) {
        return commentRepository.findCommentsByUser_UserId(userId).stream().map(commentDTOMapper).toList();
    }

    public List<CommentResponseDTO> getCommentsByThreadId(Long threadId) {
        return commentRepository.findCommentsByThreadId(threadId).stream().map(commentDTOMapper).toList();
    }

    public CommentResponseDTO addComment(CommentCreateDTO commentDTO) throws EntityNotFoundException {
        Thread thread = getObjectFromDB(commentDTO.getThreadId(), threadRepository);
        User user = getObjectFromDB(commentDTO.getUserId(), userRepository);

        Comment comment = Comment.builder()
                .commentText(commentDTO.getCommentText())
                .commentDate(LocalDate.now())
                .thread(thread)
                .user(user)
                .build();

        return commentDTOMapper.apply(commentRepository.save(comment));
    }

    public Boolean deleteComment(
            String token,
            Long id) throws EntityNotFoundException, NoAuthorizationException {

        Comment comment = getObjectFromDB(id, commentRepository);

        String userEmailFromToken = jwtService.extractUsername(jwtService.getJWT(token));
        String userRoleFormToken = jwtService.extractUserRole(jwtService.getJWT(token));

        if(!(comment.getUser().getEmail().equals(userEmailFromToken) || userRoleFormToken.equals("Admin"))) {
            throw new NoAuthorizationException("only thread author or user with role \"Admin\" can modify thread");
        }

        commentRepository.delete(comment);
        return true;
    }

    @Transactional
    public ResponseEntity<String> updateCommentById(Long commentId, Comment updatedComment) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided comment with id: " + commentId + " does not exist");
        }
        if (updatedComment.getCommentText() != null && !updatedComment.getCommentText().isEmpty() && !comment.getCommentText().equals(updatedComment.getCommentText())) {
            comment.setCommentText(updatedComment.getCommentText());
            comment.setCommentDate(LocalDate.now());
            return ResponseEntity.ok("Comment updated successfully");
        }
        return ResponseEntity.ok("No changes to comment");
    }
}
