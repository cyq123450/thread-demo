package com.cyq.spring.chapter01.demo05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @PropertySource 注解能够引入外部的配置文件，value属性指令引入文件的位置；ignoreResourceNotFound属性为一个boolean值，为true表示
 *                 在没有找到指定的文件时不会报错，为false表示没有找到指定的文件时会报错。
 */
@Configuration
@ComponentScan
@PropertySource(value = {"classpath:com/cyq/spring/chapter01/demo05/db.properties"}, ignoreResourceNotFound = false)
public class AppConfig {

    /**
     * PropertySourcesPlaceholderConfigurer类的配置会是外部引入的配置文件的属性直接通过@Value注解引入,
     *          主要的作用是为了让Spring能够解析属性占位符;
     *          如果容器中没有PropertySourcesPlaceholderConfigurer实例，只能通过容器的环境去获取属性值。
     * @return
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
