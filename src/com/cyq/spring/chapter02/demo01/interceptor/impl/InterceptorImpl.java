package com.cyq.spring.chapter02.demo01.interceptor.impl;

import com.cyq.spring.chapter02.demo01.interceptor.Interceptor;

public class InterceptorImpl implements Interceptor {
    @Override
    public void before(Object object) {
        System.out.println("准备打印角色信息...");
    }

    @Override
    public void after(Object object) {
        System.out.println("角色信息打印结束...");
    }

    @Override
    public void afterReturning(Object object) {
        System.out.println("角色信息打印成功...");
    }

    @Override
    public void afterThrowing(Object object) {
        System.out.println("角色信息打印失败...");
    }
}
