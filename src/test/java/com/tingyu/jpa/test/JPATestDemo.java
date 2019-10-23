package com.tingyu.jpa.test;

import com.tingyu.jpa.entity.Customer;
import com.tingyu.jpa.util.JPAUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * JPA测试类
 *      1.加载配置文件，创建实体管理类工厂对象
 *      2.通过实体管理工厂类对象，获取实体类管理器对象
 *      3.获取事务管理器对象，并开启事务
 *      4.完成业务逻辑（增，删，改，查）
 *      5.提交事务（异常时回）
 *      6.释放资源
 */
public class JPATestDemo {
    /**
     * 保存客户信息
     */
    @Test
    public void testSave(){

        EntityManagerFactory factory= Persistence.createEntityManagerFactory("myJpa");
        EntityManager em= factory.createEntityManager();
        EntityTransaction  tx= em.getTransaction();
        tx.begin();
        //注意：主键不能手动设置，会引起冲突
        Customer customer=new Customer(null,"adb","028-9456-1234","add");
      //  Customer customer=new Customer();
       // customer.setName("如果时间会有回音");
      //  customer.setPhone("897577");
        em.persist(customer);
        tx.commit();
        em.close();
        factory.close();
    }

    /**
     * 删除客户信息
     */
    @Test
    public void testRemove(){
        //获取实体管理类对象
        EntityManager entityManager = JPAUtils.getEntityManager();
        //获取实体事务对象并开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //执行业务方法
        //根据id查询出需要被删除的客户信息
        Customer customer = entityManager.getReference(Customer.class, 2L);
        //调用remove()方法删除该对象
        entityManager.remove(customer);
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
    }

    /**
     * 更新客户信息
     */
    @Test
    public void testMerge(){
        //获取实体管理类对象
        EntityManager entityManager = JPAUtils.getEntityManager();
        //获取实体事务对象并开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //执行业务方法
        //根据id查询出需要被删除的客户信息
        Customer customer = entityManager.getReference(Customer.class, 2L);
        customer.setName("中铁丽景书香").setPhone("13573245578").setAddress("四川省成都市温江区学海路88号");
        //调用merge()方法更新该客户记录
        entityManager.merge(customer);
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
    }
    /**
     * find()查询单个客户信息
     * 1.find()方法返回为目标类对象
     * 2.执行该方法时立即向数据库发送sql请求查询数据。
     */
    @Test
    public void testFind(){
        //获取实体管理类对象
        EntityManager entityManager = JPAUtils.getEntityManager();
        //获取实体事务对象并开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //执行业务方法
        //根据id查询出需要被删除的客户信息
        Customer customer = entityManager.find(Customer.class, 1L);
        System.out.println(customer);
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
    }

    /**
     * getReference()方法查询单个客户信息
     * 1.getReference方法返回目标类代理对象
     * 2.getReference方法查询，只有对象被调用的时候才提交sql,属于延时加载
     */
    @Test
    public void testGetReference(){
        //获取实体管理类对象
        EntityManager entityManager = JPAUtils.getEntityManager();
        //获取实体事务对象并开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //执行业务方法
        //根据id查询出需要被删除的客户信息
        Customer customer = entityManager.getReference(Customer.class, 2L);
        System.out.println(customer);
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
    }
}
