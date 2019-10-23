package com.tingyu.jpa.test;

import com.tingyu.jpa.util.JPAUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * JPQL 语言查询数据库
 */
public class JPQLTestDemo {
    /**
     * 查询所有客户信息
     * SQL: select * from t_customer
     * JPQL: from Customer
     */
    @Test
    public void testFindAll() {
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //业务逻辑
        String jpql = "FROM Customer";
        Query query = entityManager.createQuery(jpql);
        List list = query.getResultList();
        for (Object object : list)
        {
            System.out.println(object);
        }
        transaction.commit();
        entityManager.close();
    }

    /**
     * 倒序查询
     * SQL: select * from t_customer order by id desc
     * JPQL：from Customer order by id desc
     */
    @Test
    public void testOrder() {
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //业务逻辑
        String jpql = "from Customer order by id desc";
        Query query = entityManager.createQuery(jpql);
        List list = query.getResultList();
        for (Object object : list)
        {
            System.out.println(object);
        }
        transaction.commit();
        entityManager.close();
    }

    /**
     * 统计查询
     * getSigleResult()返回单值记录
     * SQL:select count(id) from t_customer
     * JPQL：select count(id) from Customer; count(1)也可以
     */
    @Test
    public void testCount() {
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //业务逻辑
        String jpql = "select count(1) from Customer";
        Query query = entityManager.createQuery(jpql);
        Long counts =(Long) query.getSingleResult();
        System.out.println(counts.toString());
        transaction.commit();
        entityManager.close();
    }

    /**
     * 分页查询
     *
     */
    @Test
    public void testPaginate() {
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //业务逻辑
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        query.setFirstResult(0);
        query.setMaxResults(2);
        List list = query.getResultList();
        for (Object object : list)
        {
            System.out.println(object);
        }
        transaction.commit();
        entityManager.close();
    }

    /**
     * 条件查询
     * JPQL：from Customer where name like ?1   注意：1表示第几个参数，也可理解为参数索引 ，从1开始
     */
    @Test
    public void testConditional() {
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //业务逻辑
        String jpql = "from Customer where name like ?1";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1,"linux%");
        List list = query.getResultList();
        for (Object object : list)
        {
            System.out.println(object);
        }
        transaction.commit();
        entityManager.close();
    }

}
