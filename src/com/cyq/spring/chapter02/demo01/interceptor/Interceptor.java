package com.cyq.spring.chapter02.demo01.interceptor;

/**
 * interceptor接口
 */
public interface Interceptor {

    void before(Object object);

    void after(Object object);

    void afterReturning(Object object);

    void afterThrowing(Object object);

}
