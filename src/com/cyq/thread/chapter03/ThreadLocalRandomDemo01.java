package com.cyq.thread.chapter03;

import java.util.concurrent.ThreadLocalRandom;

/**
 *  描述：
 *      ThreadLocalRandom是为了解决Random在并发情况下产生随机数的性能问题而产生的。
 */
public class ThreadLocalRandomDemo01 {

    public static void main(String[] args) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int i = threadLocalRandom.nextInt();
        System.out.println("i ---> " + i);
    }

}
