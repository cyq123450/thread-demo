package com.cyq.spring.chapter01.demo03;

import com.cyq.spring.chapter01.demo03.conf.ApplicationConfig;
import com.cyq.spring.chapter01.demo03.conf.MajorConfig;
import com.cyq.spring.chapter01.demo03.entity.SuperMarket;
import com.cyq.spring.chapter01.demo03.entity.User;
import com.cyq.spring.chapter01.demo03.service.TrafficService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        /*
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        SuperMarket superMarket1 = (SuperMarket) context.getBean("market");
        System.out.println("superMarket1:" + superMarket1);

        // @Autowired的歧义性
        TrafficService trafficService = context.getBean(TrafficService.class);
        trafficService.go();

        User u1 = (User) context.getBean("u1");
        System.out.println("u1:" + u1);
        */

        ApplicationContext context = new AnnotationConfigApplicationContext(MajorConfig.class);
        User user5 = (User)context.getBean("user5");
        System.out.println("user5:" + user5);
    }

}
