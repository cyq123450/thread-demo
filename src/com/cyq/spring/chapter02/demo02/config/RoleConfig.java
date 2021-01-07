package com.cyq.spring.chapter02.demo02.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy     // 开启自动代理功能
@ComponentScan(basePackages = {"com.cyq.spring.chapter02"})
public class RoleConfig {

}
