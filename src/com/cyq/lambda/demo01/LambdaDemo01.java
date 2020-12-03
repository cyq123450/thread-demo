package com.cyq.lambda.demo01;

import java.util.Arrays;
import java.util.List;

/**
 * Lambda表达式演示1
 */
public class LambdaDemo01 {

    public static void main(String[] args) {
        String[] names = new String[]{"Jack", "Tom", "Mary", "King", "Jery", "Bob", "Joe", "Tim"};

        List<String> list = Arrays.asList(names);

        // 使用foreach的打印方式
        System.out.println("foreach的打印方式：");
        for (String name : list) {
            System.out.print(name + "  ");
        }
        System.out.println();

        // 使用lambda的打印方式
        System.out.println("lambda的打印方式：");
        list.forEach((name) -> System.out.print(name + "  "));

        // 使用双冒号操作符
        System.out.println("使用双冒号打印方式：");
        list.forEach(System.out::println);
    }

}
