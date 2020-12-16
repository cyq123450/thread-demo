package com.cyq.spring.chapter01.demo04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

/**
 * @author cyq
 * @description
 * @date 2020/12/16 19:49
 */
@ComponentScan
@ImportResource({"classpath:com/cyq/spring/chapter01/demo04/spring-bean.xml"})
public class AppConfig {

    @Bean
    @Profile("dev")
    public Person person1() {
        Person person = new Person();
        person.setUserName("代码中的开发环境");
        return person;
    }

    @Bean
    @Profile("test")
    public Person person2() {
        Person person = new Person();
        person.setUserName("代码中的测试环境");
        return person;
    }

}
