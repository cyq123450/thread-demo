package com.cyq.spring.chapter01.demo04;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author cyq
 * @description
 * @date 2020/12/16 19:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
public class Main {

    @Autowired
    private User user;

    @Autowired
    private Person person;

    @Test
    public void test() {
        System.out.println("user:" + user);
        System.out.println("person:" + person);
    }

}
