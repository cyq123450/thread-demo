package com.cyq.spring.chapter01.demo03.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;

/**
 * @Import 注解中可以导入多个配置类
 * @ImportResource 注解可以引入外部xml配置文件
 */
@ComponentScan
@Import({ApplicationConfig.class})
@ImportResource({"classpath:com/cyq/spring/chapter01/demo03/conf/spring-bean.xml"})
public class MajorConfig {
}
