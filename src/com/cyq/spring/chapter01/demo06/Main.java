package com.cyq.spring.chapter01.demo06;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class Main {

    @Autowired(required = false)
    private User user;

    @Autowired(required = false)
    private User user1;



    @Test
    public void test1() {
        System.out.println("user:" + user);
    }

    @Test
    public void test2() {
        System.out.println("user == user1:" + (user == user1));
    }

}
