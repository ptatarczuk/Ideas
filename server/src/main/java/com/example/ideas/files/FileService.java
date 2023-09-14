package com.example.ideas.files;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${project.image}")
    private String filesPath;
    private final FileDownloadService fileDownloadService;


    public FileUploadResponse uploadFile(MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        long size = multipartFile.getSize();

        String filecode = null;
        try {
            filecode = saveFile(filesPath, fileName, multipartFile);
        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
            throw new IOException();
        }

        return FileUploadResponse.builder()
                .fileName(fileName)
                .size(size)
                .downloadUri("/downloadFile/" + filecode)
                .build();
    }

    public boolean deleteFile(String fileCode) throws IOException {
        Resource resource = null;
        try {
            resource = fileDownloadService.getFileAsResource(filesPath, fileCode);
            if(resource != null) {
                return resource.getFile().delete();
            }
        } catch (IOException ioe) {
//            e.printStackTrace();
//            return false;
            throw new IOException("Could not delete file: " + resource.getDescription() , ioe);
        }
        return false;
    }


    private String saveFile(String path, String fileName, MultipartFile multipartFile)
            throws IOException {
        Path uploadPath = Paths.get(path);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileCode = UUID.randomUUID().toString();

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }

        return fileCode;
    }
}

