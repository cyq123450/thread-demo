package com.cyq.lambda_java8.demo04;

import com.cyq.lambda_java8.demo02.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用：若lambda体中的内容有方法已经实现了，我们可以使用“方法引用”。可以把方法引用理解为lambda的另一种表现形式。
 *
 * 注意：函数接口中的方法的返回值和参数列表要与lambda体中的方法返回值和参数列表相同
 *
 * 主要有三种语法格式：
 *      对象::实例方法名
 *      类::静态方法名
 *      类::实例方法名
 *
 */
public class LambdaDemoTest01 {

    /**
     * 对象::实例方法名
     */
    @Test
    public void test1() {
        // 原来的形式
        Consumer<String> consumer1 = (x) -> System.out.println(x);

        /**
         * 方法引用的前提是：函数接口中的方法的返回值和参数列表要与lambda体中的方法返回值和参数列表相同
         *      void accept(T t)
         *      public void println(String x)
         */
        // 方法引用
        PrintStream ps = System.out;
        Consumer<String> consumer2 = ps::println;
        // 另一种形式
        Consumer<String> consumer3 = System.out::println;

        consumer1.accept("consumer1");
        consumer2.accept("consumer2");
        consumer3.accept("consumer3");
    }

    @Test
    public void test2() {
        Employee employee = new Employee(1, "Jack", 23);
        Supplier<String> supplier1 = () -> employee.getUserName();
        Supplier<String> supplier2 = employee::getUserName;
        Supplier<Integer> supplier3 = employee::getAge;
        System.out.println("supplier1 --> " + supplier1.get());
        System.out.println("supplier2 --> " + supplier2.get());
        System.out.println("supplier3 --> " + supplier3.get());
    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test3() {
        // 原来形式
        Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);

        /**
         * 方法引用的前提是：函数接口中的方法的返回值和参数列表要与lambda体中的方法返回值和参数列表相同
         *      int compare(T o1, T o2)
         *      public static int compare(int x, int y)
         */
        // 方法引用
        Comparator<Integer> comparator2 = Integer::compare;

        int retVal1 = comparator1.compare(1, 2);
        int retVal2 = comparator2.compare(1, 2);
        System.out.println("retVal1 --> " + retVal1);
        System.out.println("retVal2 --> " + retVal2);
    }

    /**
     * 类::实例方法名
     */
    @Test
    public void test4() {
        // 原来形式
        BiPredicate<String, String> biPredicate1 = (x, y) -> x.equals(y);

        // 方法引用
        /**
         * 通过上下作对比：第一个参数(x)为实例对象的引用，第二个参数(y)为引用方法的参数
         *      String::equals ==> x.equals(y)
         */
        BiPredicate<String, String> biPredicate2 = String::equals;

        System.out.println(biPredicate1.test("A", "b"));
        System.out.println(biPredicate2.test("A", "A"));

    }

}
