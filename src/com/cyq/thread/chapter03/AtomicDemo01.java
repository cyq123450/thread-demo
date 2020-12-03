package com.cyq.thread.chapter03;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 描述：
 *      AtomicLong是原子类，能够保证多线程之间的线程安全。
 *      但在高并发的情况下，AtomicLong还是会产生性能问题。
 */
public class AtomicDemo01 {

    private static AtomicLong count = new AtomicLong(0);

    private static class MyThread extends Thread {

        private volatile int[] arrayInt;

        public MyThread(int[] arrayInt) {
            this.arrayInt = arrayInt;
        }

        @Override
        public void run() {
            for(int i = 0; i < arrayInt.length; i++) {
                if (arrayInt[i] == 0) {
                    count.incrementAndGet();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arrayInt1 = new int[]{0, 12, 34, 0, 12, 0, 12, 34, 34, 0, 12, 4, 54, 0, 43, 0};
        int[] arrayInt2 = new int[]{0, 3, 3, 4, 0};
        MyThread t1 = new MyThread(arrayInt1);
        MyThread t2 = new MyThread(arrayInt2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Main end ---> count:" + count);
    }

}
