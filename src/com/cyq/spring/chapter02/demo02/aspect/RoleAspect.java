package com.cyq.spring.chapter02.demo02.aspect;

import org.aspectj.lang.annotation.*;

/**
 * 切面：使用@Aspect 注解的方法是一个切面
 */
@Aspect
public class RoleAspect {

    /**
     * 切入点：用来定义一个全局的却如点，各通知可以通过方法的形式引入
     */
    @Pointcut("execution(* com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    public void pointCut() {}

    /**
     * 前置通知：在连接点(某个类的某个方法)执行之前执行
     */
    @Before("execution(* com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    public void before() {
        System.out.println("前置通知执行...");
    }

    /**
     * 后置通知：在连接点(某个类的某个方法)执行之后执行，无论是否发生异常
     */
    @After("execution(* com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    public void after() {
        System.out.println("后置通知执行");
    }

    /**
     * 返回通知：在连接点(某个类某个方法)执行成功之后执行
     */
    @AfterReturning("execution(* com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    public void AfterReturning() {
        System.out.println("返回通知执行...");
    }

    /**
     * 异常通知：在连接点(某个类的某个方法)执行发生异常时执行
     */
    @AfterThrowing("execution(*  com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    public void afterThrowing() {
        System.out.println("异常通知执行...");
    }

}
