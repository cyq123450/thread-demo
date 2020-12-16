package com.cyq.spring.chapter01.demo03.conf;

import com.cyq.spring.chapter01.demo03.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * 使用注解方式配置bean需要提供一个配置类。
 * @ComponentScan 注解默认扫描该类所在包及其子类中的所有类；
 *                该注解中有两个属性：
 *                    basePackages:是一个数组的结构，会扫描当前类所在包及其子包以及basePackages提供的包及其子类中的类，
 *                    basePackageClasses:是一个数组的结构，会扫描当前类所在包及其子包以及basePackageClasses提供类当前包及其子包中的类。
 * 注意：一个类上可以有多个@ComponentScan注解，
 *      同一个@ComponentScan注解中的basePackages和basePackageClasses如果扫描有重复不会创建出重复的对象；
 *      但如果定义了多个@ComponentScan注解中的basePackages和basePackageClasses如果扫描有重复会创建出重复的对象
 */
@ComponentScan(basePackages = {"com.cyq.spring.chapter01.demo03", "com.cyq.spring.chapter01.demo03.conf"})
public class ApplicationConfig {

    /**
     * @Bean 注解只能标注在方法上，不能标注在类上。该注解表示的是把方法的返回值放入到Spring IOC容器中，name的名称作为该对象在Spring IOC
     *       容器中的ID，并且可以有多个；initMethod是初始化方法；destoryMethod是销毁调用的方法。如果name值则默认使用方法的方法名作为
     *       该实例在Spring IOC容器中的ID。
     * @return
     */
    @Bean(name = {"u1", "u2"}, initMethod = "init", destroyMethod = "destory")
    public User getUser() {
        return new User();
    }

}
