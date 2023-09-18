package com.example.ideas.thread.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
class FileWithMetaData {

    private MultipartFile file;
    private ThreadCreateDTO threadCreateDTO;
    //zmiana typu ze stringa na Threadcdto
}
