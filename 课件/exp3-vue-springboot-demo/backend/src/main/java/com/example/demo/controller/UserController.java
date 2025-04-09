package com.example.demo.controller;

import com.example.demo.model.Result;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public Result<List<User>> findAll() {
        List<User> users = userService.findAll();
        return Result.success(users);
    }
    
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            return Result.error("User not found");
        }
        return Result.success(user);
    }
    
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null) {
            return Result.error("Username already exists");
        }
        User registeredUser = userService.register(user);
        return Result.success("User registered successfully", registeredUser);
    }
    
    @PostMapping("/login")
    public Result<User> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        if (userService.login(username, password)) {
            User user = userService.findByUsername(username);
            return Result.success("Login successful", user);
        } else {
            return Result.error(401, "Invalid username or password");
        }
    }
    
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        boolean updated = userService.update(user);
        if (updated) {
            return Result.success("User updated successfully", true);
        } else {
            return Result.error("User update failed");
        }
    }
    
    @PutMapping("/{id}/password")
    public Result<Boolean> updatePassword(@PathVariable Integer id, @RequestBody Map<String, String> passwords) {
        String newPassword = passwords.get("newPassword");
        boolean updated = userService.updatePassword(id, newPassword);
        if (updated) {
            return Result.success("Password updated successfully", true);
        } else {
            return Result.error("Password update failed");
        }
    }
    
    @PostMapping("/{id}/avatar")
    public Result<String> uploadAvatar(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        try {
            String filename = FileUploadUtil.saveFile(file);
            User user = userService.findById(id);
            if (user == null) {
                return Result.error("User not found");
            }
            user.setAvatar(filename);
            boolean updated = userService.updateAvatar(id, filename);
            if (updated) {
                return Result.success("Avatar uploaded successfully", filename);
            } else {
                return Result.error("Failed to update avatar");
            }
        } catch (IOException e) {
            return Result.error("Failed to upload avatar: " + e.getMessage());
        }
    }
} 