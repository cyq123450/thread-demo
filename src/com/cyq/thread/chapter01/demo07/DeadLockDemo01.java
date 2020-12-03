package com.cyq.thread.chapter01.demo07;

/**
 * 描述：
 *      死锁发生的原因是因为两个线程各占用一个资源不释放，而又想获取对方的占用的资源，导致了一直无限等待情况，发生了死锁。
 * 总结：
 *      死锁产生与资源获取的顺序有很大的原因，资源获取的有序性可以避免死锁的产生，因为资源的有序性打破了资源的请求并持有条件和环路等待条件。
 */
public class DeadLockDemo01 {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    private static class MyThread1 extends Thread {

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("MyThread1 get lock1...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("MyThread1 get lock2...");
                    System.out.println("MyThread1 release lock2...");
                }
                System.out.println("MyThread1 release lock1...");
            }

        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println("MyThread2 get lock2...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("MyThread2 get lock2...");
                    System.out.println("MyThread2 release lock2...");
                }
                System.out.println("MyThread2 release lock2...");
            }
        }
    }

    public static void main(String[] args) {
        new MyThread1().start();
        new MyThread2().start();
    }

}
