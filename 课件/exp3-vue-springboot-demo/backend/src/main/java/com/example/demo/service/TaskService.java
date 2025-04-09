package com.example.demo.service;

import com.example.demo.mapper.TaskMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    
    @Autowired
    private TaskMapper taskMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    public List<Task> findAll() {
        List<Task> tasks = taskMapper.findAll();
        // Populate user data for each task
        for (Task task : tasks) {
            task.setUser(userMapper.findById(task.getUserId()));
        }
        return tasks;
    }
    
    public Task findById(Integer id) {
        Task task = taskMapper.findById(id);
        if (task != null) {
            task.setUser(userMapper.findById(task.getUserId()));
        }
        return task;
    }
    
    public List<Task> findByUserId(Integer userId) {
        return taskMapper.findByUserId(userId);
    }
    
    public boolean add(Task task) {
        return taskMapper.insert(task) > 0;
    }
    
    public boolean update(Task task) {
        return taskMapper.update(task) > 0;
    }
    
    public boolean delete(Integer id) {
        return taskMapper.deleteById(id) > 0;
    }
} 