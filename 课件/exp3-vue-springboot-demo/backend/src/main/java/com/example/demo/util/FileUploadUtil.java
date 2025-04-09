package com.example.demo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUploadUtil {

    // Upload directory (relative to application)
    public static final String UPLOAD_DIR = "uploads";

    /**
     * Save an uploaded file to the upload directory
     * 
     * @param file MultipartFile to save
     * @return The filename of the saved file
     * @throws IOException if saving fails
     */
    public static String saveFile(MultipartFile file) throws IOException {
        // Create the upload directory if it doesn't exist
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Generate a unique filename to prevent duplicates
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + fileExtension;
        
        // Save the file
        Path filePath = uploadPath.resolve(newFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        return newFilename;
    }
} 