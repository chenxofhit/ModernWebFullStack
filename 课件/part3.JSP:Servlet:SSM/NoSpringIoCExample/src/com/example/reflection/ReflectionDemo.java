package com.example.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射机制基础示例
 * 展示如何使用Java反射API获取类信息、创建对象、访问成员变量和调用方法
 */
public class ReflectionDemo {

    public static void main(String[] args) {
        System.out.println("====== Java反射机制示例 ======\n");
        
        try {
            // 1. 获取类的Class对象（三种方式）
            System.out.println("1. 获取Class对象的三种方式：");
            // 方式一：通过类名.class获取
            Class<?> clazz1 = Person.class;
            System.out.println("方式一：类名.class -> " + clazz1.getName());
            
            // 方式二：通过对象.getClass()获取
            Person person = new Person();
            Class<?> clazz2 = person.getClass();
            System.out.println("方式二：对象.getClass() -> " + clazz2.getName());
            
            // 方式三：通过Class.forName()获取
            Class<?> clazz3 = Class.forName("com.example.reflection.Person");
            System.out.println("方式三：Class.forName() -> " + clazz3.getName());
            System.out.println();
            
            // 2. 通过反射创建对象
            System.out.println("2. 通过反射创建对象：");
            // 使用默认构造方法
            Person p1 = (Person) clazz3.newInstance();
            System.out.println("使用默认构造函数: " + p1.getName() + ", " + p1.getAge());
            
            // 使用带参数的构造方法
            Constructor<?> constructor = clazz3.getConstructor(String.class, int.class);
            Person p2 = (Person) constructor.newInstance("张三", 25);
            System.out.println("使用带参构造函数: " + p2.getName() + ", " + p2.getAge());
            System.out.println();
            
            // 3. 通过反射访问成员变量
            System.out.println("3. 通过反射访问成员变量：");
            Field nameField = clazz3.getDeclaredField("name");
            Field ageField = clazz3.getDeclaredField("age");
            
            // 设置为可访问（绕过访问权限检查）
            nameField.setAccessible(true);
            ageField.setAccessible(true);
            
            // 获取成员变量的值
            System.out.println("获取私有变量值: name=" + nameField.get(p2) + ", age=" + ageField.get(p2));
            
            // 修改成员变量的值
            nameField.set(p2, "李四");
            ageField.set(p2, 30);
            System.out.println("修改私有变量后: name=" + nameField.get(p2) + ", age=" + ageField.get(p2));
            System.out.println();
            
            // 4. 通过反射调用方法
            System.out.println("4. 通过反射调用方法：");
            // 调用公共方法
            Method introduceMethod = clazz3.getMethod("introduce");
            System.out.println("调用公共方法:");
            introduceMethod.invoke(p2);  // 等同于 p2.introduce()
            
            // 调用私有方法
            Method secretMethod = clazz3.getDeclaredMethod("saySecret");
            secretMethod.setAccessible(true);  // 设置为可访问
            System.out.println("调用私有方法:");
            secretMethod.invoke(p2);  // 正常情况下不能直接调用私有方法
            
            // 5. 展示反射的实际应用
            System.out.println("\n5. 反射的实际应用示例：");
            // 假设我们在运行时才知道要创建什么类的对象
            String className = "com.example.reflection.Person";
            String methodName = "setName";
            
            // 根据类名创建对象并调用方法
            createAndInvokeMethod(className, methodName, "王五");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 根据类名创建对象并调用指定方法的示例
     * 这展示了Spring IoC容器的基本原理
     */
    private static void createAndInvokeMethod(String className, String methodName, Object... args) throws Exception {
        // 加载类
        Class<?> clazz = Class.forName(className);
        
        // 创建对象
        Object instance = clazz.newInstance();
        
        // 获取要调用的方法
        Method method = null;
        if (args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
            // 构建参数类型数组
            Class<?>[] paramTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, paramTypes);
        }
        
        // 调用方法
        method.invoke(instance, args);
        
        // 展示结果
        System.out.println("动态创建和调用后的对象状态:");
        Method introduceMethod = clazz.getMethod("introduce");
        introduceMethod.invoke(instance);
    }
}