package com.example.ideas.files1.controller;


import com.example.ideas.files1.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    @Value("${project.image}")
    private String path;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(
            @RequestParam("image") MultipartFile image
    ) {

        String fileName = null;
        try {
            fileName = fileService.uploadImage(path, image);


            //repository call fileName save
            System.out.println(fileName);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new FileResponse(null, "Image is not uploaded due to error on Server"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new FileResponse(fileName, "Image successfully uploaded"), HttpStatus.OK);

    }


}
