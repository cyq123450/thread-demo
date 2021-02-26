package com.cyq.java8.lambda_java8.demo04;

import org.junit.Test;

import java.util.function.Function;

/**
 * 数组引用：
 *      语法格式：Type[]::new
 */
public class LambdaDemoTest03 {

    /**
     * 数组引用
     */
    @Test
    public void test1() {
        // 原方式
        Function<Integer, String[]> function1 = (x) -> new String[x];

        // 数组引用
        Function<Integer, String[]> function2 = String[]::new;

        System.out.println("function1 --> " + function1.apply(4).length);
        System.out.println("function2 --> " + function2.apply(6).length);
    }

}
