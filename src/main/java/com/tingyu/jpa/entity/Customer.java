package com.tingyu.jpa.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 *
 */
@Entity //标注该类为实体类对象
@Table(name="t_customer") //配置实体类与数据库表的映射关系

@NoArgsConstructor //lombok注解标注无参构造
@AllArgsConstructor //lombok注解标注全参构造
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Customer {
    /**
     * 配置实体类属性与数据库表字段间的映射关系
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;//主键ID
    @Column(name="name")
    private String name;//名称
    @Column(name="phone")
    private String phone;//联系方式
    @Column(name="address")
    private String address;//联系地址
}
