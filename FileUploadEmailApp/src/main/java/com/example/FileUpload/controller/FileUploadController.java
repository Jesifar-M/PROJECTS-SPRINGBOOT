package com.example.FileUpload.controller;

import com.example.FileUpload.service.EmailService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {

    private final EmailService emailService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public FileUploadController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Please select a file");
        }

        try {

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath =
                    uploadPath.resolve(file.getOriginalFilename());

            Files.write(
                    filePath,
                    file.getBytes()
            );

            emailService.sendConfirmationEmail(
                    file.getOriginalFilename()
            );

            return ResponseEntity.ok(
                    "File uploaded successfully and confirmation email sent."
            );

        } catch (IOException e) {

            return ResponseEntity.internalServerError()
                    .body("File upload failed: " + e.getMessage());

        } catch (Exception e) {

            return ResponseEntity.internalServerError()
                    .body("File uploaded but email sending failed.");
        }
    }
}