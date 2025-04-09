package com.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射演示类
 * 展示如何通过反射访问类的私有属性和方法
 */
public class ReflectionDemo {
    
    public static void main(String[] args) {
        try {
            System.out.println("====== 反射示例 ======");
            
            // 1. 获取类的Class对象
            Class<?> studentClass = Class.forName("com.example.Student");
            System.out.println("1. 获取到Student类的Class对象");
            
            // 2. 通过反射创建对象
            Constructor<?> constructor = studentClass.getConstructor(String.class, int.class);
            Student student = (Student) constructor.newInstance("张三", 18);
            System.out.println("2. 通过反射创建Student对象");
            
            // 3. 访问私有属性
            Field nameField = studentClass.getDeclaredField("name");
            nameField.setAccessible(true); // 设置可访问
            String name = (String) nameField.get(student);
            System.out.println("3. 通过反射获取私有属性name: " + name);
            
            // 4. 修改私有属性
            nameField.set(student, "李四");
            System.out.println("4. 通过反射修改私有属性name为: 李四");
            
            // 5. 调用私有方法
            Method studyMethod = studentClass.getDeclaredMethod("study");
            studyMethod.setAccessible(true); // 设置可访问
            studyMethod.invoke(student);
            System.out.println("5. 通过反射调用私有方法study()");
            
            // 6. 调用公共方法
            Method sayHelloMethod = studentClass.getMethod("sayHello");
            sayHelloMethod.invoke(student);
            System.out.println("6. 通过反射调用公共方法sayHello()");
            
            System.out.println("\n====== 反射的作用 ======");
            System.out.println("1. 可以在运行时获取类的信息");
            System.out.println("2. 可以访问和修改私有成员");
            System.out.println("3. 可以在运行时动态创建对象");
            System.out.println("4. 可以在运行时动态调用方法");
            System.out.println("5. 为框架开发提供基础支持");
            
        } catch (Exception e) {
            System.out.println("反射操作出错：" + e.getMessage());
            e.printStackTrace();
        }
    }
} 