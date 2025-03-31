package com.example;

/**
 * 用户服务接口
 */
public interface UserService {
    
    /**
     * 根据ID查找用户
     * @param id 用户ID
     * @return 用户名
     */
    String getUserById(int id);
    
    /**
     * 创建新用户
     * @param name 用户名
     * @return 是否创建成功
     */
    boolean createUser(String name);
} 