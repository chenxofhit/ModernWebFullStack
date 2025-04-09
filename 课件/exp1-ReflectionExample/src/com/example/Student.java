package com.example;

/**
 * 一个简单的学生类，用于演示反射
 */
public class Student {
    // 私有属性
    private String name;
    private int age;
    
    // 私有方法
    private void study() {
        System.out.println(name + "正在学习...");
    }
    
    // 公共方法
    public void sayHello() {
        System.out.println("你好，我是" + name + "，今年" + age + "岁");
    }
    
    // 构造函数
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
} 