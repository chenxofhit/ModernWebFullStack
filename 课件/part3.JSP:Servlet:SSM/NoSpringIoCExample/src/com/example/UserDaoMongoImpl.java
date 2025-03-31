package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * UserDao的MongoDB实现类 - 用于展示切换实现的困难
 */
public class UserDaoMongoImpl implements UserDao {
    
    // 模拟MongoDB数据存储
    private Map<Integer, String> mongoDatabase = new HashMap<>();
    
    public UserDaoMongoImpl() {
        System.out.println("正在初始化MongoDB连接...");
        // 初始化一些测试数据
        mongoDatabase.put(1, "张三(Mongo)");
        mongoDatabase.put(2, "李四(Mongo)");
        mongoDatabase.put(3, "王五(Mongo)");
    }
    
    @Override
    public String findNameById(int id) {
        System.out.println("正在访问MongoDB查询用户ID: " + id);
        // 模拟MongoDB查询延时
        try {
            Thread.sleep(50); // MongoDB比传统数据库快一些
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mongoDatabase.get(id);
    }
    
    @Override
    public boolean save(String name) {
        System.out.println("正在保存用户到MongoDB: " + name);
        // 模拟MongoDB保存操作
        try {
            Thread.sleep(100); // MongoDB保存比传统数据库快一些
            int newId = mongoDatabase.size() + 1;
            mongoDatabase.put(newId, name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 