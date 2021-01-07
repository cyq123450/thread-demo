package com.cyq.spring.chapter02.demo03;

import com.cyq.spring.chapter02.demo03.conf.AspectConfig;
import com.cyq.spring.chapter02.demo03.service.RoleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AspectConfig.class);
        RoleService roleService = context.getBean(RoleService.class);
        roleService.printRole();
    }

}
