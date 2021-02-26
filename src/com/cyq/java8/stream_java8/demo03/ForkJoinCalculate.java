package com.cyq.java8.stream_java8.demo03;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join模式
 */
// 继承RecursiveTask抽象类
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    public ForkJoinCalculate() {

    }

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= 100000) {
            Long sum = 0L;
            for(Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            Long spar = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, spar);
            left.fork();
            ForkJoinCalculate right = new ForkJoinCalculate(spar + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }

    // 测试
    public static void main(String[] args) {
        // 使用Fork/Join计算1——100000000累加和
        long forkStart = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask forkJoinTask = new ForkJoinCalculate(0L, 100000000L);
        Long retVal = (Long)forkJoinPool.invoke(forkJoinTask);
        System.out.println("retVal : " + retVal);
        long forkEnd = System.currentTimeMillis();
        System.out.println("Fork/Join spend time : " + (forkEnd - forkStart) + "ms");

        // 普通方式计算1——100000000累加和
        long originStart = System.currentTimeMillis();
        long sum = 0L;
        for(int i = 0; i <= 100000000; i++) {
            sum += i;
        }
        System.out.println("Origin Sum :" + sum);
        long originEnd = System.currentTimeMillis();
        System.out.println("Origin spend time : " + (originEnd - originStart) + "ms");


        /**
         * 获取当前时间的另一种方式
         */
        Instant start = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("纳秒时间 : " + duration.toNanos());
        System.out.println("毫秒时间 : " + duration.toMillis());
        System.out.println("秒时间 : " + duration.getSeconds());
    }

}
