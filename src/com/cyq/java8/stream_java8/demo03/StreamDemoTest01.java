package com.cyq.java8.stream_java8.demo03;

import org.junit.Test;

import java.util.stream.LongStream;

/**
 * 并行流和串行流
 */
public class StreamDemoTest01 {

    @Test
    public void test1() {
        long retVal = LongStream.rangeClosed(0L, 100000000000L)
                //.parallel()     // 并行流
                .sequential()   // 串行流
                .reduce(0, Long::sum);
        System.out.println("retVal : " + retVal);
    }

}
