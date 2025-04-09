package com.example;

/**
 * 使用工厂模式的应用程序主类
 * 这是一种手动实现IoC的尝试，但仍有局限性
 */
public class FactoryApplication {

    public static void main(String[] args) {
        System.out.println("====== 使用工厂模式改进的应用示例 ======");
        System.out.println("这个例子展示了没有使用Spring IoC时的一种改进尝试");
        
        // 首先使用默认DAO实现
        System.out.println("\n----- 使用默认数据库实现 -----");
        UserService userService = new BetterUserServiceImpl();
        testService(userService);
        
        // 切换到MongoDB实现
        System.out.println("\n----- 切换到MongoDB实现 -----");
        DaoFactory.setUseMongoDb(true);
        
        // 注意：已经创建的Service实例不会受影响，因为依赖已经注入
        System.out.println("\n* 使用已有Service实例 (不会改变其Dao实现):");
        testService(userService);
        
        // 必须创建新的Service实例才能使用新的DAO实现
        System.out.println("\n* 创建新的Service实例 (使用新的Dao实现):");
        UserService newUserService = new BetterUserServiceImpl();
        testService(newUserService);
        
        System.out.println("\n====== 工厂模式的局限性 ======");
        System.out.println("1. 已创建的对象无法感知到实现变化");
        System.out.println("2. 工厂类数量会随着应用程序规模增长而膨胀");
        System.out.println("3. 依赖关系仍然分散在代码中，不便于统一管理");
        System.out.println("4. 对象的生命周期管理困难");
        System.out.println("5. 缺乏AOP等高级特性支持");
    }
    
    private static void testService(UserService service) {
        // 测试用户查询
        System.out.println(service.getUserById(1));
        
        // 测试用户创建
        service.createUser("测试用户");
    }
} 