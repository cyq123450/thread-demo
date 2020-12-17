package com.cyq.spring.chapter01.demo07;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * SpEL表达式
 */
public class SpelExpressionParserTest {

    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        // 设置表达式
        Expression expression = parser.parseExpression("'hello world'");
        System.out.println(expression.getValue());

        // 通过EL访问普通方法
        expression = parser.parseExpression("'hello world'.charAt(0)");
        System.out.println(expression.getValue());

        // 通过EL访问的getter方法
        expression = parser.parseExpression("'hello world'.bytes");
        System.out.println((byte[])expression.getValue());

        // 通过EL访问属性
        expression = parser.parseExpression("'hello world'.getBytes().length");
        System.out.println(expression.getValue());

        expression = parser.parseExpression("new String('asdf')");
        System.out.println(expression.getValue());
    }

}
