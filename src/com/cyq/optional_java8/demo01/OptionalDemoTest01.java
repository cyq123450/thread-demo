package com.cyq.optional_java8.demo01;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional容器类常用方法：
 *      Optional.of(T t) : 创建一个Optional实例
 *      Optional.empty() : 创建一个空的Optional实例
 *      Optional.ofNullable(T t) : 若t不为null，创建Optional实例，否则创建空实例
 *      isPresent() : 判断是否包含值
 *      orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
 *      orElseGet(Supplier s) : 如果调用对象包含值，返回该值，否则返回s获取的值
 *      map(Function f) : 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
 *      flatMap(Function mapper) : 与map类似，要求返回值必须是Optional
 */
public class OptionalDemoTest01 {

    /**
     * Optional.of(T t) : 创建一个Optional实例
     */
    @Test
    public void test1() {
        String str = new String("asd");
        Optional<String> op1 = Optional.of(str);
        System.out.println(op1.get());

        str = null;
        Optional<String> op2 = Optional.of(str);    // 该行报NullPointerException
        System.out.println(op2.get());
    }

    /**
     * Optional.empty() : 创建一个空的Optional实例
     */
    @Test
    public void test2() {
        Optional<Object> op = Optional.empty();
        System.out.println(op.get());       // 该行报NoSuchElementException
    }

    /**
     *  Optional.ofNullable(T t) : 若t不为null，创建Optional实例，否则创建空实例
     */
    @Test
    public void test3() {
        String str = "123";
        Optional<String> op1 = Optional.ofNullable(str);
        System.out.println(op1.get());

        str = null;
        Optional<String> op2 = Optional.ofNullable(str);
        System.out.println(op2.get());      // 改行报NoSuchElementException
    }

    /**
     * isPresent() : 判断是否包含值
     */
    @Test
    public void test4() {
        Optional<String> op = Optional.of("123");
        if (op.isPresent()) {
            System.out.println("have value...");
        } else {
            System.out.println("no value...");
        }
    }

    /**
     * orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
     */
    @Test
    public void test5() {
        String str = new String("123");
        Optional<String> op1 = Optional.of(str);
        String retVal1 = op1.orElse("q4");
        System.out.println(retVal1);

        str = null;
        Optional<String> op2 = Optional.ofNullable(str);
        String retVal2 = op2.orElse("q4");
        System.out.println(retVal2);
    }

    /**
     * orElseGet(Supplier s) : 如果调用对象包含值，返回该值，否则返回s获取的值
     */
    @Test
    public void test6() {
        Optional<String> op1 = Optional.of("123");
        String retVal1 = op1.orElseGet(() -> "5");
        System.out.println(retVal1);

        Optional<Object> op2 = Optional.empty();
        Object retVla2 = op2.orElseGet(() -> "5");
        System.out.println(retVla2);
    }

    /**
     * map(Function f) : 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     */
    @Test
    public void test7() {
        Optional<String> op1 = Optional.of("123");
        Optional<String> retVal1 = op1.map((s) -> s + "qwer");
        System.out.println(retVal1.get());


        Optional<Object> op2 = Optional.empty();
        Optional<String> retVal2 = op2.map((s) -> s + "qwer");
        System.out.println(retVal2.get());      // 该行报NoSuchElementException
    }

    /**
     * flatMap(Function mapper) : 与map类似，要求返回值必须是Optional
     */
    @Test
    public void test8() {
        Optional<String> op1 = Optional.of("123");
        Optional<String> s1 = op1.flatMap((s) -> Optional.of(s + "^_^!!!"));
        System.out.println(s1.get());

        Optional<Object> op2 = Optional.empty();
        Optional<String> s2 = op2.flatMap((s) -> Optional.of(s + "^_^!!!"));
        System.out.println(s2.get());       // 改行报NoSuchElementException
    }

}
