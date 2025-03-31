package com.example;

/**
 * 用户控制器类 - 模拟表现层组件
 * 在这个类中，我们直接创建了UserService的实例，展示硬编码依赖的问题
 */
public class UserController {
    
    // 硬编码依赖服务层组件
    private UserService userService = new UserServiceImpl();
    
    /**
     * 处理用户查询请求
     * @param userId 用户ID
     */
    public void handleUserQuery(int userId) {
        System.out.println("接收到用户查询请求: ID=" + userId);
        
        // 调用服务层进行业务处理
        String userName = userService.getUserById(userId);
        
        System.out.println("查询结果: " + userName);
        System.out.println("--------------------");
    }
    
    /**
     * 处理用户创建请求
     * @param userName 用户名
     */
    public void handleUserCreation(String userName) {
        System.out.println("接收到用户创建请求: Name=" + userName);
        
        // 调用服务层进行业务处理
        boolean success = userService.createUser(userName);
        
        System.out.println("用户创建" + (success ? "成功" : "失败"));
        System.out.println("--------------------");
    }
} 