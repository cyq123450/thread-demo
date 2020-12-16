package com.cyq.spring.chapter01.demo03.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Autowired 注解可以帮助自动注入属性值，该注解不仅放在属性上，还可以放在方法上；
 *            可以通过required的属性值来决定是否一定要注入，默认值为true，当找不到需要注入的属性值会抛出异常；
 *            在required的值设置为false时，在没有找到对应的注入值不会报错，属性值为null，但要注意空指针的判断
 */
@Component("market")
public class SuperMarket {
    @Value("时代超市")
    private String name;
    // @Autowired(required = false)
    @Autowired
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    // @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SuperMarket{" +
                "name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
