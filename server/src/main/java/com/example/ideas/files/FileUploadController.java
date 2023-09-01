package com.example.ideas.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
public class FileUploadController {

    @Value("${project.image}")
    private String filePath;
    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile multipartFile) {

//        if (multipartFile == null || multipartFile.isEmpty()) {
//            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
//        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        long size = multipartFile.getSize();

        String filecode = null;
        try {
            filecode = fileUploadService.saveFile(filePath, fileName, multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("saving file failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(fileName)
                .size(size)
                .downloadUri("/downloadFile/" + filecode)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
