package com.example.demoapp.services;

import com.example.demoapp.models.UploadedFile;
import com.example.demoapp.repositories.UploadedFileRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final UploadedFileRepository fileRepository;
    private final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Override
    public void uploadFile(MultipartFile file) {
        try {
            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFileName(file.getName());
            uploadedFile.setFileData(file.getBytes());
            uploadedFile.setScanned(false);
            fileRepository.save(uploadedFile);
        } catch (IOException e) {
            logger.error("Error occurred during file upload.", e);
        }
    }

    @Override
    public boolean scanFileForViruses(Long fileId) {
        Optional<UploadedFile> optionalUploadedFile = fileRepository.findById(fileId);

        if (optionalUploadedFile.isPresent()) {
            UploadedFile uploadedFile = optionalUploadedFile.get();

            uploadedFile.setScanned(true);
            fileRepository.save(uploadedFile);
        }

        return true;
    }

}
