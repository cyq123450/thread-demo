package com.cyq.spring.chapter02.demo02.aspect;

import com.cyq.spring.chapter02.demo02.service.RoleVerifier;
import com.cyq.spring.chapter02.demo02.service.impl.RoleVerifierImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面：使用@Aspect 注解的方法是一个切面
 */
@Aspect
@Component
public class RoleAspect {

    /**
     * 该注解的value：表示是对RoleServiceImpl类进行增强，
     * defaultImpl：表示默认的实现类
     */
    @DeclareParents(value = "com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl+", defaultImpl = RoleVerifierImpl.class)
    public RoleVerifier roleVerifier;

    /**
     * 切入点：用来定义一个全局的却如点，各通知可以通过方法的形式引入
     */
    @Pointcut("execution(* com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    public void print() {}

    /**
     * 前置通知：在连接点(某个类的某个方法)执行之前执行
     */
    @Before("execution(* com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    // @Before("print()")
    public void before() {
        System.out.println("前置通知执行...");
    }

    /**
     * 后置通知：在连接点(某个类的某个方法)执行之后执行，无论是否发生异常
     */
    @After("execution(* com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    // @After("print()")
    public void after() {
        System.out.println("后置通知执行...");
    }

    /**
     * 返回通知：在连接点(某个类某个方法)执行成功之后执行
     */
    @AfterReturning("execution(* com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    // @AfterReturning("print()")
    public void AfterReturning() {
        System.out.println("返回通知执行...");
    }

    /**
     * 异常通知：在连接点(某个类的某个方法)执行发生异常时执行
     */
    @AfterThrowing("execution(*  com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    // @AfterThrowing("print()")
    public void afterThrowing() {
        System.out.println("异常通知执行...");
    }

    @Around("execution(*  com.cyq.spring.chapter02.demo02.service.impl.RoleServiceImpl.printRole(..))")
    public void around(ProceedingJoinPoint pj) {
        System.out.println("around before...");
        try {
             pj.proceed();
            System.out.println("around afterReturning");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("around throwing");
        } finally {
            System.out.println("around after");
        }
    }

}
