package com.cyq.java8.lambda_java8.demo02;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * lambda表达式基本语法：java8中引入了一个新的操作符 ->
 * -> 操作符分为左侧和右侧两部分，
 * 左侧：Lambda 表达式参数列表
 * 右侧：Lambda 表达式中所要执行的功能，即Lambda体
 *
 * 语法一：无参数，无返回值
 *        eg：() -> System.out.print("Hello World");
 *
 * 语法二：一个参数，无返回值
 *        eg：(x) -> System.out.print("Hello World");
 *
 * 语法三：一个参数，可以不写括号
 *        eg：x -> System.out.print("Hello World");
 *
 * 语法四：两个及两个以上参数，有返回值，并且lambda体中有多条语句
 *        eg：(x, y) -> {
 *            语句一;
 *            语句二;...;
 *            return 返回值;
 *        }
 *
 * 语法五：两个及两个以上参数，有返回值；如果lambda体中只有一条语句，则大括号可以不写
 *        eg：(x, y) -> x.compareTo(y);
 *
 * 语法六：lambda表达式中的参数类型可以不写，因为JVM编译器可以根据上下文信息推断出对应的数据类型，俗称“类型推断”
 *         JDK1.8及以上才有推断功能。
 *         eg: (Integer x, Integer y) -> x.compareTo(y);
 *
 * 总结：Lambda表达式需要函数式接口的支持，所谓的函数式接口就是接口中只有一个抽象的方法。
 *      函数式接口可以使用@FunctionalInterface注解修饰，该注解用来检查该类是否为函数式接口。
 *
 */
public class LambdaDemoTest01 {

    /**
     * 无参数，无返回值
     *      () -> System.out.println("Hello World");
     */
    @Test
    public void test1() {
        int num = 0;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World..." + num);     // 在JDK1.7及以前num局部变量必须显式声明为final,
                                                                // 但在JDK1.8不需要显式声明，默认就是final的。
            }
        };
        r1.run();
        System.out.println("-----------------------------");
        Runnable r2 = () -> System.out.println("Hello World..." + num);
        r2.run();
    }

    /**
     * 一个参数，无返回值
     *      (x) -> System.out.println(x);
     */
    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("Hello World...");
    }

    /**
     * 一个参数，无返回值(一个参数可以不用写小括号)
     *      x -> System.out.println(x);
     */
    @Test
    public void test3() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("Hello World...");
    }

    /**
     * 两个及两个以上参数，有返回值，并且lambda体中有多条语句
     * (x, y) -> {
     *     语句;...;
     *     return 返回值;
     * }
     */
    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("语句一...");
            System.out.println("语句二...");
            return x.compareTo(y);
        };
    }

    /**
     * 两个及两个以上参数，有返回值；如果lambda体中只有一条语句，则大括号可以不写
     *      eg：(x, y) -> x.compareTo(y);
     */
    @Test
    public void test5() {
        Comparator<Integer> comparator = (x, y) -> x.compareTo(y);
    }

    /**
     * 函数式接口编程
     */
    @Test
    public void test6() {
        int retVal1 = operationNum(100, (int x) -> x * x);
        System.out.println("retVal1 --> " + retVal1);
        System.out.println("retVal2 --> " + operationNum(100, (y) -> y + 100));
    }

    private int operationNum(int num, MyFun myFun) {
        return myFun.getVal(num);
    }

}
