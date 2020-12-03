package com.cyq.thread.chapter02.demo02;

/**
 * 描述：
 *      为了对代码进行优化，在不影响数据结果的前提下，JVM会对编译后的指令进行重排序。
 *      重排序对单线程不会产生任何问题，但对于多线程会产生非预期结果。
 *      volatile可以避免重排序和内存可见性问题。
 *      volatile可以确保写之前的指令不会被重排序到写之后，读之前不会被重排序到写之后。
 */
public class OrderDemo01 {

    private static int num = 0;
    private static boolean flag = false;

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (flag) {
                    System.out.println(num + num);
                }
            }
            System.out.println("MyThread1 end...");
        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            num = 2;
            flag = true;
            System.out.println("MyThread2 end...");
        }
    }

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();

        myThread1.start();
        myThread2.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread1.interrupt();
    }

}
