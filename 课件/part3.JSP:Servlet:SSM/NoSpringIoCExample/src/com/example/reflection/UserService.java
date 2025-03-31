package com.example.reflection;

/**
 * 用户服务类 - 使用自定义注解实现依赖注入
 */
public class UserService {
    
    // 使用自定义注解标记需要自动注入的字段
    @SimpleAutowired("userRepository")
    private UserRepository userRepository;
    
    /**
     * 获取用户名
     * @param id 用户ID
     * @return 用户名
     */
    public String getUserName(int id) {
        System.out.println("UserService: 获取用户信息，ID=" + id);
        
        if (id <= 0) {
            return "无效的用户ID";
        }
        
        String name = userRepository.findNameById(id);
        return name != null ? name : "用户不存在";
    }
    
    /**
     * 创建新用户
     * @param name 用户名
     * @return 是否创建成功
     */
    public boolean createUser(String name) {
        System.out.println("UserService: 创建新用户，名称=" + name);
        
        if (name == null || name.trim().isEmpty()) {
            System.out.println("用户名不能为空");
            return false;
        }
        
        return userRepository.save(name);
    }
} 