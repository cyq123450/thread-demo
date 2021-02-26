package com.cyq.java8.lambda_java8.demo04;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用
 *      语法格式：ClassName::new
 *
 * 需要调用的构造器参数列表要与函数接口里的抽象方法的参数列表一致。
 *
 */
public class LambdaDemoTest02 {

    /**
     * 构造器引用
     */
    @Test
    public void test1() {
        // 原方式
        Student student1 = new Student();

        // 构造器引用：使用无参构造
        Supplier<Student> supplier = Student::new;

        // 构造器引用：使用一个参数的构造器
        Function<String, Student> function= Student::new;

        // 构造器引用：使用两个参数的构造器
        BiFunction<String, Integer, Student> biFunction = Student::new;

        System.out.println(student1);
        System.out.println(supplier.get());
        System.out.println(function.apply("Jack"));
        System.out.println(biFunction.apply("Tom", 23));
    }

}
