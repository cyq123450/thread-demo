package com.cyq.spring.chapter01.demo04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyq
 * @description
 * @date 2020/12/16 19:54
 */
public class Main {



    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = context.getBean(User.class);
        System.out.println("user:" + user);
        Person person = context.getBean(Person.class);
        System.out.println("person:" + person);
    }

}
