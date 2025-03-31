package com.example.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个极简的IoC容器实现
 * 展示反射技术如何用于依赖注入（控制反转）
 */
public class SimpleIoCContainer {
    
    // 保存所有的单例对象
    private Map<String, Object> beans = new HashMap<>();
    
    /**
     * 注册一个bean到容器
     * @param beanName bean的名称
     * @param bean bean对象
     */
    public void registerBean(String beanName, Object bean) {
        beans.put(beanName, bean);
    }
    
    /**
     * 根据bean名称获取实例
     * @param beanName bean的名称
     * @return bean对象
     */
    public Object getBean(String beanName) {
        return beans.get(beanName);
    }
    
    /**
     * 自动装配依赖关系
     * 查找带有@SimpleAutowired注解的字段，并注入对应的bean
     */
    public void autowireBean(Object bean) throws Exception {
        Class<?> clazz = bean.getClass();
        
        // 遍历所有字段
        for (Field field : clazz.getDeclaredFields()) {
            // 检查字段是否有@SimpleAutowired注解
            if (field.isAnnotationPresent(SimpleAutowired.class)) {
                // 获取需要注入的bean名称
                SimpleAutowired autowired = field.getAnnotation(SimpleAutowired.class);
                String beanName = autowired.value();
                
                // 如果没有指定bean名称，就使用字段的类型名作为bean名称
                if (beanName.isEmpty()) {
                    beanName = field.getType().getSimpleName().toLowerCase();
                }
                
                // 获取要注入的对象
                Object dependencyBean = getBean(beanName);
                if (dependencyBean != null) {
                    // 设置为可访问（绕过访问修饰符限制）
                    field.setAccessible(true);
                    // 注入依赖
                    field.set(bean, dependencyBean);
                } else {
                    throw new RuntimeException("找不到名为 " + beanName + " 的bean");
                }
            }
        }
    }
} 