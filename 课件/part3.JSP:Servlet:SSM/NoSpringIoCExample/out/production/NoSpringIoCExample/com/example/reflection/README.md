# Java反射和简易IoC容器示例

这个包中的代码展示了Java反射机制的基本使用，以及如何利用反射技术实现一个简单的IoC容器。

## 包含内容

1. **基础反射示例** - `ReflectionDemo.java`
   - 展示如何获取Class对象
   - 通过反射创建对象
   - 访问类的字段和方法
   - 操作私有成员

2. **简易IoC容器实现** - `SimpleIoCContainer.java`
   - 通过反射实现依赖注入
   - 使用自定义注解标记需要注入的依赖
   - 模拟Spring框架核心IoC功能

3. **依赖注入示例**
   - `UserRepository.java` - 数据访问接口
   - `UserRepositoryImpl.java` - 标准实现
   - `UserRepositoryMongoImpl.java` - 替代实现
   - `UserService.java` - 使用注解声明依赖的服务类

4. **运行示例**
   - `SimpleIoCDemo.java` - 基本IoC容器演示
   - `SimpleIoCSwapDemo.java` - 演示实现类切换

## 关键概念

1. **反射**：Java反射机制允许程序在运行时获取类的信息，动态地创建对象、访问和修改字段以及调用方法，即使这些成员是私有的。

2. **控制反转(IoC)**：将对象创建和依赖关系管理的控制权从代码中移交给外部容器。

3. **依赖注入(DI)**：IoC的一种常见实现方式，由容器负责将对象的依赖项"注入"到对象中。

## 运行方法

1. 运行 `ReflectionDemo` 查看基本反射API的使用
2. 运行 `SimpleIoCDemo` 查看简单IoC容器的工作原理
3. 运行 `SimpleIoCSwapDemo` 查看如何在不修改代码的情况下切换实现

## 学习要点

通过这些示例，您可以了解：

1. 反射如何用于动态获取和操作类的信息
2. Spring框架IoC容器的底层工作原理
3. 注解如何与反射结合实现依赖注入
4. 控制反转和依赖注入如何帮助实现松耦合设计

这些示例为初学者提供了对反射和IoC核心概念的基本理解，是深入学习Spring框架的基础。 