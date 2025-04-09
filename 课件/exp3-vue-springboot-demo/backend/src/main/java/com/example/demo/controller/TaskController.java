package com.example.demo.controller;

import com.example.demo.model.Result;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import com.example.demo.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @GetMapping
    public Result<List<Task>> findAll() {
        List<Task> tasks = taskService.findAll();
        return Result.success(tasks);
    }
    
    @GetMapping("/{id}")
    public Result<Task> findById(@PathVariable Integer id) {
        Task task = taskService.findById(id);
        if (task == null) {
            return Result.error("Task not found");
        }
        return Result.success(task);
    }
    
    @GetMapping("/user/{userId}")
    public Result<List<Task>> findByUserId(@PathVariable Integer userId) {
        List<Task> tasks = taskService.findByUserId(userId);
        return Result.success(tasks);
    }
    
    @PostMapping
    public Result<Boolean> add(@RequestBody Task task) {
        boolean added = taskService.add(task);
        if (added) {
            return Result.success("Task added successfully", true);
        } else {
            return Result.error("Failed to add task");
        }
    }
    
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Integer id, @RequestBody Task task) {
        task.setId(id);
        boolean updated = taskService.update(task);
        if (updated) {
            return Result.success("Task updated successfully", true);
        } else {
            return Result.error("Failed to update task");
        }
    }
    
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Integer id) {
        boolean deleted = taskService.delete(id);
        if (deleted) {
            return Result.success("Task deleted successfully", true);
        } else {
            return Result.error("Failed to delete task");
        }
    }
    
    @PostMapping("/{id}/attachment")
    public Result<String> uploadAttachment(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        try {
            String filename = FileUploadUtil.saveFile(file);
            Task task = taskService.findById(id);
            task.setAttachment(filename);
            taskService.update(task);
            return Result.success("Attachment uploaded successfully", filename);
        } catch (IOException e) {
            return Result.error("Failed to upload attachment: " + e.getMessage());
        }
    }
} 