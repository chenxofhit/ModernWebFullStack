package com.example;

/**
 * 改进的UserService实现 - 使用工厂获取依赖
 * 虽然改进了一些，但仍然有很多问题
 */
public class BetterUserServiceImpl implements UserService {
    
    // 使用工厂获取Dao实例，但仍然是在构造时固定的
    private UserDao userDao;
    
    public BetterUserServiceImpl() {
        // 虽然使用了工厂，但仍然是在构造函数中创建，之后无法替换
        this.userDao = DaoFactory.getUserDao();
    }
    
    @Override
    public String getUserById(int id) {
        System.out.println("改进的UserService: 查询用户信息, ID=" + id);
        
        if (id <= 0) {
            return "无效的用户ID";
        }
        
        String userName = userDao.findNameById(id);
        return userName != null ? userName : "用户不存在";
    }
    
    @Override
    public boolean createUser(String name) {
        System.out.println("改进的UserService: 创建新用户, 名称=" + name);
        
        if (name == null || name.trim().isEmpty()) {
            System.out.println("用户名不能为空");
            return false;
        }
        
        if (name.length() < 2) {
            System.out.println("用户名长度不能小于2个字符");
            return false;
        }
        
        return userDao.save(name);
    }
} 