package com.example;

/**
 * UserService接口的实现类
 * 在这个类中，我们直接创建了UserDao的实例，展示硬编码依赖的问题
 */
public class UserServiceImpl implements UserService {
    
    // 直接在类中创建依赖对象的实例 - 硬编码依赖
    private UserDao userDao = new UserDaoImpl();
    
    @Override
    public String getUserById(int id) {
        System.out.println("UserService: 查询用户信息, ID=" + id);
        // 添加一些业务逻辑，如权限验证、日志记录等
        if (id <= 0) {
            return "无效的用户ID";
        }
        
        // 调用DAO层获取数据
        String userName = userDao.findNameById(id);
        return userName != null ? userName : "用户不存在";
    }
    
    @Override
    public boolean createUser(String name) {
        System.out.println("UserService: 创建新用户, 名称=" + name);
        // 业务逻辑处理
        if (name == null || name.trim().isEmpty()) {
            System.out.println("用户名不能为空");
            return false;
        }
        
        if (name.length() < 2) {
            System.out.println("用户名长度不能小于2个字符");
            return false;
        }
        
        // 调用DAO层保存数据
        return userDao.save(name);
    }
} 