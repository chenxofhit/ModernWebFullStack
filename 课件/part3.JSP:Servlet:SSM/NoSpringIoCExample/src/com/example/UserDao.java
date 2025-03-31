package com.example;

/**
 * 用户数据访问接口
 */
public interface UserDao {
    
    /**
     * 根据ID查找用户
     * @param id 用户ID
     * @return 用户名
     */
    String findNameById(int id);
    
    /**
     * 保存用户
     * @param name 用户名
     * @return 是否保存成功
     */
    boolean save(String name);
} 