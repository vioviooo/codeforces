package com.example.codeforces.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private final Path uploadDir = Paths.get("uploads/profile-pictures");

    public String saveProfilePicture(MultipartFile file, Long userId) throws IOException {
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String filename = userId + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(filename);

        Files.write(filePath, file.getBytes());

        return "/uploads/profile-pictures/" + filename;
    }
}