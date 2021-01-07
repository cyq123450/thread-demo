package com.cyq.spring.chapter02.demo03.conf;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)   // 用来指定该切面的执行顺序
public class RoleAspect1 {

    @Before("execution(* com.cyq.spring.chapter02.demo03.service.impl.RoleServiceImpl.*(..))")
    public void before() {
        System.out.println("RoleAspect1 --> before...");
    }

    @After("execution(* com.cyq.spring.chapter02.demo03.service.impl.RoleServiceImpl.*(..))")
    public void after() {
        System.out.println("RoleAspect1 --> after...");
    }

    @AfterReturning("execution(* com.cyq.spring.chapter02.demo03.service.impl.RoleServiceImpl.*(..))")
    public void afterReturning() {
        System.out.println("RoleAspect1 --> afterReturning...");
    }

    @AfterThrowing("execution(* com.cyq.spring.chapter02.demo03.service.impl.RoleServiceImpl.*(..))")
    public void afterThrowing() {
        System.out.println("RoleAspect1 --> afterThrowing...");
    }

}
