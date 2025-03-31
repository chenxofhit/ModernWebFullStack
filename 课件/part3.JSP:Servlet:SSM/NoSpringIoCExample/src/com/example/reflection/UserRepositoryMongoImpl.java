package com.example.reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * UserRepository的MongoDB实现
 * 用于演示如何在不修改UserService代码的情况下切换实现
 */
public class UserRepositoryMongoImpl implements UserRepository {
    
    // 模拟MongoDB数据库
    private Map<Integer, String> mongoDatabase = new HashMap<>();
    
    public UserRepositoryMongoImpl() {
        System.out.println("初始化MongoDB连接...");
        // 初始化一些测试数据
        mongoDatabase.put(1, "张三(Mongo)");
        mongoDatabase.put(2, "李四(Mongo)");
        mongoDatabase.put(3, "王五(Mongo)");
    }

    @Override
    public String findNameById(int id) {
        System.out.println("UserRepositoryMongoImpl: 从MongoDB查询用户ID=" + id);
        // 模拟MongoDB查询更快
        return mongoDatabase.get(id);
    }

    @Override
    public boolean save(String name) {
        System.out.println("UserRepositoryMongoImpl: 保存用户到MongoDB: " + name);
        // 模拟MongoDB保存
        int newId = mongoDatabase.size() + 1;
        mongoDatabase.put(newId, name);
        return true;
    }
} 