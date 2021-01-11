package com.cyq.lambda.demo03;

import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *  Java8内置的四大核心函数式接口
 *      Consumer<T> : 消费型接口
 *          void accept(T t);
 *      Supplier<T> : 供给型接口
 *          T get();
 *      Function<T, R> : 函数型接口
 *          R apply(T t);
 *      Predicate<T> : 断言型接口
 *          boolean test(T t);
 */
public class LambdaDemoTest01 {

    /**
     * Consumer<T> : 消费型接口
     */
    @Test
    public void test1() {
        consumer(1000, (x) -> System.out.println("消费 --> " + x + "元"));
    }

    /**
     * Supplier<T> : 攻击性接口
     */
    @Test
    public void test2() {
        Long time = supplier(() -> {
            return System.currentTimeMillis();
        });
        System.out.println("time --> " + time);
    }

    /**
     * Function<T> : 函数型接口
     */
    @Test
    public void test3() {
        String retVal = function("asdfgh", (x) -> x.toUpperCase());
        System.out.println("retVal --> " + retVal);
    }

    /**
     * Perdicate<T> : 断言型接口
     */
    @Test
    public void test4() {
        boolean retVal = predicate(100, (x) -> {
            if (x > 100) {
                return true;
            } else {
                return false;
            }
        });
        System.out.println("retVal --> " + retVal);
    }

    private void consumer(Integer num, Consumer<Integer> consumer) {
        consumer.accept(num);
    }

    private Long supplier(Supplier<Long> supplier) {
        return supplier.get();
    }

    private String function(String str,Function<String, String> function) {
        return function.apply(str);
    }

    private boolean predicate(int num, Predicate<Integer> predicate) {
        return predicate.test(num);
    }

}
