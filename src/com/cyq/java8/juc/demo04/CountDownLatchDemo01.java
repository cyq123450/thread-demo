package com.cyq.java8.juc.demo04;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch:闭锁。在完成某些计算时，只有其他所有线程全部运算完成后，当前线程才能继续执行。
 */
public class CountDownLatchDemo01 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        MyThread1 myThread1 = new MyThread1(countDownLatch);
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 10; i++) {
            new Thread(myThread1).start();
        }
        try {
            countDownLatch.await();  // 等待所有线程全部执行完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("spent time : " + (endTime - startTime) + "ms");
    }

}

class MyThread1 implements Runnable {

    private CountDownLatch countDownLatch;

    public MyThread1(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 50000; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);

                }
            }
        } finally {
            countDownLatch.countDown();
        }
    }
}
