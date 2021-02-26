package com.cyq.java8.juc.demo09;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程池演示。
 *      线程调度
 */
public class ThreadPooledDemo02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

        for(int i = 0; i < 5; i++) {
            ScheduledFuture<Integer> future = service.schedule(() -> {
                int result = new Random().nextInt(100);
                System.out.println(Thread.currentThread().getName() + " --> " + result);
                return result;
            }, 5, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " result return --> " + future.get());
        }

        service.shutdown();
    }

}
