package com.example.demoapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    void uploadFile(MultipartFile file);
    boolean scanFileForViruses(Long fileId);
}
