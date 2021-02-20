package com.cyq.juc;

import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join模式演示。
 *      RecursiveTask：带返回值
 *      RecursiveAction：不带返回值
 *
 *      利用Fork/Join模式演示从1加到100000000，并且临界值为10000
 */
public class ForkJoinDemo01 {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        MyForkJoin myForkJoin = new MyForkJoin(1, 100000000);
        Long result = pool.invoke(myForkJoin);
        System.out.println(result);
    }

}

class MyForkJoin extends RecursiveTask<Long> {

    // 开始值
    private long start;
    // 结束值
    private long end;

    // 拆分临界值
    private final long SHURHOLD = 10000L;

    public MyForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if (end - start <= SHURHOLD) {
            for(long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            MyForkJoin leftJoin = new MyForkJoin(start, middle);
            leftJoin.fork();    // 拆分
            MyForkJoin rightJoin = new MyForkJoin(middle + 1, end);
            rightJoin.fork();

            // 合并结果集
            return leftJoin.join() + rightJoin.join();
        }
    }

}
