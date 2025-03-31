package com.example.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解：用于标记需要自动装配的字段
 * 模拟Spring的@Autowired注解
 */
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时可用
@Target(ElementType.FIELD) // 注解只能用在字段上
public @interface SimpleAutowired {
    
    /**
     * bean的名称，默认为空字符串
     * 当为空时，将使用字段类型的简单名称作为bean名称
     */
    String value() default "";
} 