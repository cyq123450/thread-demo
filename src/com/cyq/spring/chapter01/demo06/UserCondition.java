package com.cyq.spring.chapter01.demo06;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 当使用一个类作为条件的判断需要实现Condition接口并重写matches方法。
 * matches方法中的conditionContext参数名是Spring容器的上下文，annotatedTypeMetadata参数是对应的注解类型参数。
 * matches返回true表示条件成立，返回false表示条件不成立。
 */
public class UserCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment environment = conditionContext.getEnvironment();
        return environment.containsProperty("user.id") & environment.containsProperty("user.userName");
    }
}
