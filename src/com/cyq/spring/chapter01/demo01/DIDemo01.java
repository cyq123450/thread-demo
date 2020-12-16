package com.cyq.spring.chapter01.demo01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cyq
 * @description 使用构造器注入对象
 * @date 2020/12/15 21:19
 */
public class DIDemo01 {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/cyq/spring/chapter01/demo01/spring-bean.xml");
        // 1.通过构造方法注入属性值
        User user1 = (User) context.getBean("user1");
        System.out.println("user1:" + user1);

        // 2.通过setter方法注入属性值
        User user2 = (User) context.getBean("user2");
        System.out.println("user2:" + user2);

        // 没有指定id的对象获取
        User user3 = (User) context.getBean("com.cyq.spring.chapter01.demo01.User#0");
        System.out.println("user3:" + user3);

        // 获取通过ref引入属性值的实例
        School school1 = (School) context.getBean("school1");
        System.out.println("school1:" + school1);
    }

}
