package com.example.reflection;

/**
 * 用户仓库接口 - 模拟数据访问对象
 */
public interface UserRepository {
    
    /**
     * 通过ID查询用户名
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