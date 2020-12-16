package com.cyq.spring.chapter01.demo03.service.impl;

import com.cyq.spring.chapter01.demo03.entity.User;
import com.cyq.spring.chapter01.demo03.service.TrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @Autowired 注入属性值是根据类型进行注入的，如果同一个类型有多个实现类，那么在注入时就会抛出异常；
 *            可以通过@Primary注解标注表示该类是首要被需要注入的，同一个类型的实现类可以有多个实例添加@Primary注解，这样同样会存在歧义；
 *            相对于有参的构造器，构造器的参数可以通过@Autowired注入。
 *            @Autowired 注解可以配合@Qualifier注解使用，@Qualifier注解可以名称注入的是id为哪一个的实例。
 */
@Component
@Primary
public class CarServiceImpl implements TrafficService {

    @Autowired
    @Qualifier("user")
    private User user = null;

    @Override
    public void go() {
        System.out.println("汽车跑...");
    }
}
