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
        // 1.通过构造方法注入的方式获取对象
        User user1 = (User) context.getBean("user1");
        System.out.println("user1:" + user1);
    }

}
