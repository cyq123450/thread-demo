package com.cyq.spring.chapter01.demo02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/cyq/spring/chapter01/demo02/spring-bean.xml");
        // 集合属性注入的简单那方式
        ComplexCollection complexCollection1 = (ComplexCollection) context.getBean("complexCollection1");
        System.out.println("complexCollection1:" + complexCollection1);
        // 集合属性注入的复杂方式
        UserRoleVO userRoleVO1 = (UserRoleVO) context.getBean("userRoleVO1");
        System.out.println("userRoleVO1:" + userRoleVO1);
        // 通过命名空间的方式注入对象的属性值
        UserRoleVO userRoleVO2 = (UserRoleVO) context.getBean("userRoleVO2");
        System.out.println("userRoleVO2:" + userRoleVO2);
    }

}
