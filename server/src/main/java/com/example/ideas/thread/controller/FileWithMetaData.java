package com.example.ideas.thread.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileWithMetaData {

    private MultipartFile file;
    private String threadCreateDTO;
//    private ThreadCreateDTO threadCreateDTO;
}
