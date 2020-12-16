package com.cyq.spring.chapter01.demo03.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @component 注解的作用是把该类放入到Spring IOC容器中，可以通过value设置该对象在Spring IOC容器中id；在不设置的情况下，该对象在Spring
 *            IOC容器中的id为类的首字母小写。
 * @Value 注解可以直接注入属性值
 */
// @Component(value = "user")
// @Component("user")
@Component
public class User {
    @Value("1001")
    private String id;
    @Value("李四")
    private String userName;
    @Value("这位是李四")
    private String note;

    public void init() {
        System.out.println("User init()...");
    }

    public void destory() {
        System.out.println("User destory()...");
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
