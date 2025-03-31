package com.example.reflection;

/**
 * 简单的Person类，用于演示反射操作
 */
public class Person {
    // 私有成员变量
    private String name;
    private int age;
    
    // 无参构造函数
    public Person() {
        this.name = "未知";
        this.age = 0;
    }
    
    // 有参构造函数
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // 私有方法
    private void saySecret() {
        System.out.println("这是一个私有方法，外部不应该能直接访问！");
    }
    
    // 公共方法
    public void introduce() {
        System.out.println("大家好，我叫" + name + "，今年" + age + "岁。");
    }
    
    // getter和setter
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
} 