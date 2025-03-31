package com.example;

/**
 * DAO工厂类 - 尝试使用工厂模式解决硬编码依赖问题
 * 这是手动实现IoC的一种尝试，但仍有许多局限性
 */
public class DaoFactory {
    
    // 保存是否使用MongoDB的配置
    private static boolean useMongoDb = false;
    
    /**
     * 获取UserDao实例
     * @return UserDao实现
     */
    public static UserDao getUserDao() {
        if (useMongoDb) {
            return new UserDaoMongoImpl();
        } else {
            return new UserDaoImpl();
        }
    }
    
    /**
     * 设置是否使用MongoDB
     * @param useMongo 是否使用MongoDB
     */
    public static void setUseMongoDb(boolean useMongo) {
        useMongoDb = useMongo;
    }
} 