package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * UserDao接口的具体实现类
 */
public class UserDaoImpl implements UserDao {
    
    // 模拟数据库存储
    private Map<Integer, String> userDatabase = new HashMap<>();
    
    public UserDaoImpl() {
        // 初始化一些测试数据
        userDatabase.put(1, "张三");
        userDatabase.put(2, "李四");
        userDatabase.put(3, "王五");
    }
    
    @Override
    public String findNameById(int id) {
        System.out.println("正在访问数据库查询用户ID: " + id);
        // 模拟数据库查询延时
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDatabase.get(id);
    }
    
    @Override
    public boolean save(String name) {
        System.out.println("正在保存用户: " + name);
        // 模拟数据库保存操作
        try {
            Thread.sleep(200);
            int newId = userDatabase.size() + 1;
            userDatabase.put(newId, name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 