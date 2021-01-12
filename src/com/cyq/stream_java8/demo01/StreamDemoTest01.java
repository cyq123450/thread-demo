package com.cyq.stream_java8.demo01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Stream操作的三步骤：
 *      1.创建stream
 *      2.中间操作
 *      3.终止操作
 */
public class StreamDemoTest01 {

    /**
     * 1.创建stream
     */
    @Test
    public void test1() {
        // 1.通过collection系列的stream()和parallel()获取流
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        // 2.通过Arrays的静态方法stream()获取流
        Object[] arrays = new Object[5];
        Stream<Object> stream2 = Arrays.stream(arrays);

        // 3.通过Stream类中的of()获取流
        Stream<String> stream3 = Stream.of("a", "b", "c");

        // 4.创建无限流
        // 迭代
        Stream<Integer> stream4 = Stream.iterate(6, (x) -> 8 + x);  // seed:代表种子，表示从几开始
        // stream4.forEach(System.out::println);       // 数据一直打印,每次数值加8
        stream4.limit(5).forEach(System.out::println);   // 6 14 22 30 38

        // 生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(5).forEach(System.out::println);
    }

}
