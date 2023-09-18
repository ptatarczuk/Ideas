package com.example.ideas.thread.service;

import com.example.ideas.exception.EntityNotFoundException;
import com.example.ideas.exception.NoAuthorizationException;
import com.example.ideas.files.FileService;
import com.example.ideas.files.FileUploadResponse;
import com.example.ideas.security.config.JwtService;
import com.example.ideas.thread.controller.ThreadCreateDTO;
import com.example.ideas.thread.controller.ThreadUpdateDTO;
import com.example.ideas.thread.model.Thread;
import com.example.ideas.thread.repository.ThreadRepository;
import com.example.ideas.user.model.User;
import com.example.ideas.user.repository.UserRepository;
import com.example.ideas.util_Entities.category.model.Category;
import com.example.ideas.util_Entities.category.repository.CategoryRepository;
import com.example.ideas.util_Entities.stage.model.Stage;
import com.example.ideas.util_Entities.stage.repository.StageRepository;
import com.example.ideas.util_Entities.status.model.Status;
import com.example.ideas.util_Entities.status.repository.StatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.ideas.thread.utils.ObjectProvider.getObjectFromDB;

@Service
@RequiredArgsConstructor
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final StageRepository stageRepository;
    private final StatusRepository statusRepository;
    private final FileService fileService;
    private final JwtService jwtService;

    public List<Thread> getThreads() {
        return threadRepository.findAll();
    }

    public Optional<Thread> getThreadById(Long id) {
        return threadRepository.findById(id);
    }

    //Walidacja?
    public Thread addThread(MultipartFile multipartFile, ThreadCreateDTO threadDTO) throws EntityNotFoundException, IOException {

        User user = userRepository.findUserByEmail(threadDTO.getUserEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + threadDTO.getUserEmail()));
        Category category = getObjectFromDB(threadDTO.getCategoryId(), categoryRepository);
        Stage stage = getObjectFromDB(1L, stageRepository);
        Status status = getObjectFromDB(1L, statusRepository);

        Thread thread = Thread.builder()
                .date(LocalDate.now())
                .title(threadDTO.getTitle())
                .description(threadDTO.getDescription())
                .justification(threadDTO.getJustification())
                .user(user)
                .category(category)
                .stage(stage)
                .status(status)
                .build();

        if (!(multipartFile == null || multipartFile.isEmpty())) {
            FileUploadResponse fileUploadResponse = fileService.uploadFile(multipartFile);
            thread.setPhoto(fileUploadResponse.getDownloadUri());
        }

        return threadRepository.save(thread);
    }

    public ResponseEntity<Thread> deleteThread(Long id) {
        Optional<Thread> threadOptional = threadRepository.findById(id);
        if (threadOptional.isPresent()) {
            threadRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(threadOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Walidacja?
    @Transactional
    public Thread updateThreadById(
            String token,
            Long threadId, MultipartFile multipartFile, ThreadUpdateDTO threadUpdateDTO) throws IOException, EntityNotFoundException, NoAuthorizationException {

        Thread thread = getObjectFromDB(threadId, threadRepository);

        if (!(thread.getUser().getEmail().equals(getUserEmail(token)) || getUserRole(token).equals("Admin"))) {
            throw new NoAuthorizationException("only thread author or user with role \"Admin\" can modify thread");
        }

        Long categoryId = threadUpdateDTO.getCategoryId();
        Long stageId = threadUpdateDTO.getStageId();
        Long statusId = threadUpdateDTO.getStatusId();

        if (threadUpdateDTO.getTitle() != null) {
            thread.setTitle(threadUpdateDTO.getTitle());
        }
        if (threadUpdateDTO.getDescription() != null) {
            thread.setDescription(threadUpdateDTO.getDescription());
        }
        if (threadUpdateDTO.getJustification() != null) {
            thread.setJustification(threadUpdateDTO.getJustification());
        }
        if (!(multipartFile == null || multipartFile.isEmpty())) {
            uploadNewPhoto(multipartFile, thread);
        }
        if (threadUpdateDTO.getPoints() != null) {
            thread.setPoints(threadUpdateDTO.getPoints());
        }
        if (categoryId != null) {
            thread.setCategory(getObjectFromDB(categoryId, categoryRepository));
        }
        if (stageId != null) {
            thread.setStage(getObjectFromDB(stageId, stageRepository));
        }
        if (statusId != null) {
            thread.setStatus(getObjectFromDB(statusId, statusRepository));
        }

        return thread;
    }

//    @Transactional
//    public Thread updateThreadById(
//            Long threadId, ThreadUpdateDTO threadUpdateDTO) throws IOException, EntityNotFoundException, NoAuthorizationException {
//
//        Thread thread = getObjectFromDB(threadId, threadRepository);
//
//        Long categoryId = threadUpdateDTO.getCategoryId();
//        Long stageId = threadUpdateDTO.getStageId();
//        Long statusId = threadUpdateDTO.getStatusId();
//
//        if (threadUpdateDTO.getTitle() != null) {
//            thread.setTitle(threadUpdateDTO.getTitle());
//        }
//        if (threadUpdateDTO.getDescription() != null) {
//            thread.setDescription(threadUpdateDTO.getDescription());
//        }
//        if (threadUpdateDTO.getJustification() != null) {
//            thread.setJustification(threadUpdateDTO.getJustification());
//        }
//        if (threadUpdateDTO.getPoints() != null) {
//            thread.setPoints(threadUpdateDTO.getPoints());
//        }
//        if (categoryId != null) {
//            thread.setCategory(getObjectFromDB(categoryId, categoryRepository));
//        }
//        if (stageId != null) {
//            thread.setStage(getObjectFromDB(stageId, stageRepository));
//        }
//        if (statusId != null) {
//            thread.setStatus(getObjectFromDB(statusId, statusRepository));
//        }
//
//        return thread;
//    }

    public String getUserRole(String token) {
        String jwt = jwtService.getJWT(token);
        return jwtService.extractUserRole(jwt);
    }

    public String getUserEmail(String token) {
        String jwt = jwtService.getJWT(token);
        return jwtService.extractUsername(jwt);
    }


    private void uploadNewPhoto(MultipartFile multipartFile, Thread thread) throws IOException {
        deleteOldPhotoIfPresent(thread);
        FileUploadResponse fileUploadResponse = fileService.uploadFile(multipartFile);
        if (fileUploadResponse != null) {
            thread.setPhoto(fileUploadResponse.getDownloadUri());
        }
    }

    private void deleteOldPhotoIfPresent(Thread thread) throws IOException {
        String downloadUri = thread.getPhoto();
        if (!downloadUri.isEmpty()) {
            String fileCode = downloadUri.substring(downloadUri.lastIndexOf("/") + 1);
            fileService.deleteFile(fileCode);
        }
    }


//    public ResponseEntity<?> updateThreadById999(Long threadId, Map<String, Object> fields) {
//        Thread thread = threadRepository.findById(threadId).orElse(null);
//        if (thread == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided Thread with id:" + threadId + " does not exist");
//        }
//        fields.forEach((key, value) -> {
//            Field field = ReflectionUtils.findField(Thread.class, key);
//            if (field != null) {
//                field.setAccessible(true);
//                ReflectionUtils.setField(field, thread, value);
//            }
//        });
//        return ResponseEntity.ok(threadRepository.save(thread));
//    }

}

