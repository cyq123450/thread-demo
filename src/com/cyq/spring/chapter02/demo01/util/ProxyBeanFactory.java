package com.cyq.spring.chapter02.demo01.util;

import com.cyq.spring.chapter02.demo01.interceptor.Interceptor;

public class ProxyBeanFactory {

    public static <T> T getBean(T obj, Interceptor interceptor) {
        return (T) ProxyBeanUtil.getBean(obj, interceptor);
    }

}
