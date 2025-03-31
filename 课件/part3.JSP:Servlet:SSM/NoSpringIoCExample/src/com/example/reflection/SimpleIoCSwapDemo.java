package com.example.reflection;

/**
 * 简易IoC容器实现类切换演示
 * 展示如何在不修改使用方代码的情况下切换依赖实现
 */
public class SimpleIoCSwapDemo {

    public static void main(String[] args) {
        System.out.println("====== 基于反射的IoC容器实现切换示例 ======\n");
        
        try {
            // 创建IoC容器
            SimpleIoCContainer container = new SimpleIoCContainer();
            
            System.out.println("1. 使用普通数据库实现:");
            // 注册普通的UserRepository实现
            container.registerBean("userRepository", new UserRepositoryImpl());
            
            // 创建并注册UserService
            UserService userService = new UserService();
            container.registerBean("userService", userService);
            
            // 自动装配依赖
            container.autowireBean(userService);
            
            // 测试
            testService(userService);
            
            System.out.println("\n2. 切换到MongoDB实现:");
            // 替换为MongoDB实现
            container.registerBean("userRepository", new UserRepositoryMongoImpl());
            
            // 创建新的Service实例
            UserService newUserService = new UserService();
            container.registerBean("newUserService", newUserService);
            
            // 自动装配依赖
            container.autowireBean(newUserService);
            
            // 测试
            testService(newUserService);
            
            System.out.println("\n====== IoC的核心优势 ======");
            System.out.println("1. UserService代码完全不需要修改，就可以切换底层实现");
            System.out.println("2. UserService不需要关心UserRepository的具体实现类");
            System.out.println("3. 依赖关系由容器管理，而不是硬编码在代码中");
            System.out.println("4. 实现了关注点分离和松耦合设计");
            System.out.println("5. 这正是Spring IoC容器的核心思想，但Spring提供了更强大的功能");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void testService(UserService service) {
        // 测试查询
        String userName = service.getUserName(1);
        System.out.println("用户查询结果: " + userName);
        
        // 测试创建
        boolean created = service.createUser("测试用户");
        System.out.println("创建用户: " + (created ? "成功" : "失败"));
    }
} 