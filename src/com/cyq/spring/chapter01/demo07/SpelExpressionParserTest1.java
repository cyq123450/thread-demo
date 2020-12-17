package com.cyq.spring.chapter01.demo07;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * SpEL表达式
 */
public class SpelExpressionParserTest1 {

    public static void main(String[] args) {
        // 创建角色对象
        Role role = new Role();
        role.setId("1001");
        role.setNote("这是一个角色");
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'这是一个角色'");
        // 相当于从role中获取备注信息
        String note = (String) expression.getValue(role);
        System.out.println("note:" + note);

        // 环境变量类，并且将角色对象role作为其根节点
        StandardEvaluationContext ctx = new StandardEvaluationContext(role);
        // 环境变量类操作根节点
        parser.parseExpression("note").setValue(ctx, "new_note");
        // 获取备注，这里的String.class指明，希望返回一个字符串
        note = parser.parseExpression("note").getValue(ctx, String.class);
        System.out.println("新的note:" + note);

        // 调用getId方法
        String id = parser.parseExpression("getId()").getValue(ctx, String.class);
        System.out.println("id:" + id);

        // 新增环境变量
        List list = new ArrayList();
        list.add("v1");
        list.add("v2");
        // 给变量环境增加变量
        ctx.setVariable("list", list);
        // 通过表达式去读/写环境变量的值
        parser.parseExpression("#list[1]").setValue(ctx, "update_value2");
        String value = parser.parseExpression("#list[1]").getValue(ctx, String.class);
        System.out.println("value:" + value);

    }

}
