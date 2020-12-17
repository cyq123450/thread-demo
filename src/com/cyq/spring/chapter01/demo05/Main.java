package com.cyq.spring.chapter01.demo05;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class Main {

    @Autowired
    @Qualifier("d1")
    private DataSource d1;

    @Test
    public void test() {
        System.out.println("d1:" + d1);
    }

    @Test
    public void test1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        String url = context.getEnvironment().getProperty("player.url");
        System.out.println("url:" + url);
    }

}
