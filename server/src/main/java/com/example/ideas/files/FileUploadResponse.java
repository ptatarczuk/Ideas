package com.example.ideas.files;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private long size;

}
