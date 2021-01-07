package com.cyq.spring.chapter02.demo02;

import com.cyq.spring.chapter02.demo02.config.RoleConfig;
import com.cyq.spring.chapter02.demo02.entity.Role;
import com.cyq.spring.chapter02.demo02.service.RoleService;
import com.cyq.spring.chapter02.demo02.service.RoleVerifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 主启动类
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RoleConfig.class);
        RoleService roleService = context.getBean(RoleService.class);
        Role role = null;
        RoleVerifier roleVerifier = (RoleVerifier)roleService;
        if (roleVerifier.verify(role)) {
            roleService.printRole(role);
        }
    }

}
