package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public List<User> findAll() {
        return userMapper.findAll();
    }
    
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
    
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    
    public User register(User user) {
        // In a real application, you would hash the password
        // For simplicity, we're storing it in plain text
        return userMapper.insert(user);
    }
    
    public boolean update(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }
        
        // Get the existing user
        User existingUser = userMapper.findById(user.getId());
        if (existingUser == null) {
            return false;
        }
        
        // Only update non-null fields
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getAvatar() != null) {
            existingUser.setAvatar(user.getAvatar());
        }
        
        return userMapper.update(existingUser) > 0;
    }
    
    public boolean updatePassword(Integer id, String newPassword) {
        return userMapper.updatePassword(id, newPassword) > 0;
    }
    
    public boolean login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return false;
        }
        // In a real application, you would compare hashed passwords
        return user.getPassword().equals(password);
    }
    
    public boolean updateAvatar(Integer id, String avatar) {
        return userMapper.updateAvatar(id, avatar) > 0;
    }
} 