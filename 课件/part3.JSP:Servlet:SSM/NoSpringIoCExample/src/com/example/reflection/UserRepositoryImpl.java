package com.example.reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * UserRepository接口的实现类
 */
public class UserRepositoryImpl implements UserRepository {
    
    // 模拟数据库
    private Map<Integer, String> database = new HashMap<>();
    
    public UserRepositoryImpl() {
        // 初始化一些测试数据
        database.put(1, "张三");
        database.put(2, "李四");
        database.put(3, "王五");
    }

    @Override
    public String findNameById(int id) {
        System.out.println("UserRepositoryImpl: 查询用户ID=" + id);
        return database.get(id);
    }

    @Override
    public boolean save(String name) {
        System.out.println("UserRepositoryImpl: 保存用户 " + name);
        int newId = database.size() + 1;
        database.put(newId, name);
        return true;
    }
} 