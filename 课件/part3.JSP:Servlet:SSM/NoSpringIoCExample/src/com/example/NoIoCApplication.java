package com.example;

/**
 * 应用程序主类 - 展示不使用Spring IoC的情况下的依赖管理
 */
public class NoIoCApplication {

    public static void main(String[] args) {
        System.out.println("====== 不使用Spring IoC的应用示例 ======");
        
        // 直接创建控制器实例
        UserController userController = new UserController();
        
        // 测试用户查询
        userController.handleUserQuery(1);
        userController.handleUserQuery(2);
        userController.handleUserQuery(100); // 不存在的用户
        
        // 测试用户创建
        userController.handleUserCreation("赵六");
        userController.handleUserCreation("钱七");
        userController.handleUserCreation(""); // 测试无效名称
        
        System.out.println("\n====== 应用程序问题分析 ======");
        System.out.println("1. 组件间存在硬编码依赖，高度耦合");
        System.out.println("2. 难以替换实现类，例如无法轻松切换数据库访问方式");
        System.out.println("3. 难以进行单元测试，因为依赖无法被模拟");
        System.out.println("4. 每个组件负责创建和管理它的依赖，职责不单一");
        System.out.println("5. 配置集中管理困难，修改实现需要改动代码");
    }
} 