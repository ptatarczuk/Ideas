package com.example.ideas.files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileDownloadService {
    private Path foundFile;

    public Resource getFileAsResource(String filesPath, String fileCode) throws IOException {
        Path dirPath = Paths.get(filesPath);

        Files.list(dirPath).forEach(file -> {
            if (encodeFilename(file.getFileName().toString()).startsWith(fileCode)) {
                foundFile = file;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }

    public String encodeFilename(String filename) {
        return URLEncoder.encode(filename, StandardCharsets.UTF_8);
    }
}
