package com.cyq.java8.optional_java8.demo02;

/**
 * Java8中可以有default修饰的方法以及静态方法
 */
public interface MyInterface {

    /**
     * default修饰的方法实现
     */
    default void show() {
        System.out.println("这是默认的方法实现...");
    }

    static void say() {
        System.out.println("这是静态修饰的方法实现...");
    }

}
