package com.cyq.lambda.demo03;

import java.util.Arrays;

/**
 * 遍历names
 */
public class LambdaDemo01 {

    public static void main(String[] args) {
        String[] names = {"Jack", "Tom", "Tim", "Mary", "Jery"};

        // 1.使用迭代器遍历的方式
        System.out.println("1.使用迭代器的方式遍历:");
        for(String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();

        // 2.使用Arrays.toString打印
        System.out.println("2.使用Arrays.toString打印:");
        System.out.println(Arrays.toString(names));;

        // 3.使用Lambda的方式打印
        System.out.println("3.使用Lambda的方式打印:");
        Arrays.asList(names).stream()
                .forEach(name -> System.out.print(name + "  "));
        System.out.println();

        // 4.使用双冒号的方式打印(此现象较静态引用)
        System.out.println("4.使用双冒号的方式打印:");
        Arrays.asList(names).stream()
                .forEach(System.out::print);

    }

}
