package com.cyq.spring.chapter02.demo03.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.Assert;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.cyq.spring.chapter02.demo03"})
public class AspectConfig {

    /**
     * 定义多切面的情况，多个切面之间没有对应的执行顺序，每一个执行顺序都是随机的。可以通过@Order 注解给每个切面添加对应的执行顺序.
     * 除了使用@Order 注解给每个切面标注顺序外还可以让对应的切面类实现Order接口，并重写对应的getOrder方法。
     */

    @Bean
    public RoleAspect1 roleAspect1() {
        return new RoleAspect1();
    }

    @Bean
    public RoleAspect2 roleAspect2() {
        return new RoleAspect2();
    }

    @Bean
    public RoleAspect3 roleAspect3() {
        return new RoleAspect3();
    }

}
