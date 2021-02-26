package com.cyq.java8.lambda_java8.demo01;

import java.util.Comparator;

/**
 * 测试类
 */
public class LambdaDemoTest01 {

    /**
     * 案例一：演示匿名内部类的创建
     */
    /**
     * 平常创建匿名内部类的方式
     */
    public void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
    }

    /**
     * 使用Lambda表达式的方式创建(只需要编写发最核心的表达式即可)
     */
    public void test2() {
        Comparator<Integer> comparator = (x, y) -> x.compareTo(y);
    }

}
