package com.cyq.spring.chapter01.demo06;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @PropertySource 注解可以将外部的属性文件导入到Spring上下文中，ignoreResourceNotFound属性可以配置在找不到对应的文件时是否报错；
 *                 当Spring IOC容器中有PropertySourcesPlaceholderConfigurer类的实例时，可以通过占位符(@Value("${key}"))的方式引入对应的属性值；
 *                 当引入的文件没有对应的占位符属性时，为了防止对象对象报错，可以通过@Conditional注解解决
 *  @Conditional 当对对象的属性使用占位符的方式填充数据时，由于不知道外部配置文件是否包含相关的属性值，为了防治法创建对象失败，需要通过条件
 *               判断来确定是否在Spring IOC容器中创建该对象，可以通过@Conditional注解来加入条件判断，判断条件引入的类需要实现Condition
 *               接口，并重写matches。
 * @Scope 该注解用来表示创建对象的作用域
 */
@Configuration
@ComponentScan
@PropertySource(value = {"classpath:com/cyq/spring/chapter01/demo06/user.properties"}, ignoreResourceNotFound = true)
public class AppConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Conditional(UserCondition.class)
    public User user() {
        return new User();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
