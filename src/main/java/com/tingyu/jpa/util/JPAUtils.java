package com.tingyu.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA工具类：由于JPA获取EntityManagerFactory对象是一个比较消耗资源的过程，并且多线程条件下，
 * 对EntityManagerFactory对象的操作是安全的，因此为了提高程序性能，可以在调用EntityManager时，
 * 只加载一次，避免EntityManagerFactory频繁地被创建和销毁
 */
public class JPAUtils {
    private static final String PERSISTENCE_UNIT_NAME="myJpa";
    private static EntityManagerFactory factory=null;
    static {
        factory= Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    /**
     * 获取实体管理类对象实例
     * @return
     */
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }

    /**
     * 释放资源
     */
    public static void close(){
        factory.close();
    }
}
