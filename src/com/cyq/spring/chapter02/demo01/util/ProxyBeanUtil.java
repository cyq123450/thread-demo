package com.cyq.spring.chapter02.demo01.util;

import com.cyq.spring.chapter02.demo01.interceptor.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBeanUtil implements InvocationHandler {
    // 被代理对象
    private Object object;
    // 拦截器
    private Interceptor interceptor = null;

    /**
     * 获取动态代理对象
     * @param obj
     * @param interceptor
     * @return
     */
    public static Object getBean(Object obj, Interceptor interceptor) {
        ProxyBeanUtil _this = new ProxyBeanUtil();
        _this.object = obj;
        _this.interceptor = interceptor;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), _this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object retObj = null;
        boolean exceptionFlag = false;
        interceptor.before(object);
        try {
            retObj = method.invoke(object, args);
            interceptor.afterReturning(object);
        } catch (Exception e) {
            e.printStackTrace();
            interceptor.afterThrowing(object);
        } finally {
            interceptor.after(object);
        }

        return retObj;
    }
}
