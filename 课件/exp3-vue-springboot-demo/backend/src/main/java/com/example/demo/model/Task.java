package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {
    private Integer id;
    private String title;
    private String description;
    private String status;  // TODO, IN_PROGRESS, DONE
    private Integer userId;
    private String attachment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Additional fields that are not in the database
    private User user;
} 