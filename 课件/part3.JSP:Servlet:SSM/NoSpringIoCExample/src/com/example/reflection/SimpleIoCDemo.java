package com.example.reflection;

/**
 * 简易IoC容器演示
 * 展示如何使用反射技术实现控制反转和依赖注入
 */
public class SimpleIoCDemo {

    public static void main(String[] args) {
        System.out.println("====== 基于反射的简易IoC容器示例 ======\n");
        
        try {
            // 创建IoC容器
            SimpleIoCContainer container = new SimpleIoCContainer();
            
            // 1. 向容器注册bean
            System.out.println("1. 注册bean到容器");
            // 注册UserRepository实现
            UserRepository userRepository = new UserRepositoryImpl();
            container.registerBean("userRepository", userRepository);
            
            // 注册UserService
            UserService userService = new UserService();
            container.registerBean("userService", userService);
            
            // 2. 自动装配依赖关系
            System.out.println("2. 自动装配依赖");
            container.autowireBean(userService);
            
            // 3. 测试IoC容器中的bean
            System.out.println("\n3. 测试依赖注入是否成功:");
            
            // 调用service方法（内部会使用自动注入的repository）
            String userName = userService.getUserName(1);
            System.out.println("用户名: " + userName);
            
            boolean created = userService.createUser("赵六");
            System.out.println("创建用户: " + (created ? "成功" : "失败"));
            
            // 4. 展示IoC的优势
            System.out.println("\n4. 展示IoC的优势:");
            System.out.println("- 对象之间的依赖关系由容器管理，实现了松耦合");
            System.out.println("- UserService不需要知道UserRepository的具体实现");
            System.out.println("- 可以轻松更换UserRepository的实现而不修改UserService代码");
            System.out.println("- 这就是Spring框架IoC的核心思想");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 