package com.cyq.thread.chapter01.demo02;

/**
 * 总结：
 *      notify():只会使一个调用wait()方法的线程停止阻塞。
 *      notifyAll():会使全部调用wait()方法的线程停止阻塞。
 */
public class WaitAndNotifyDemo05 {

    private static volatile Object lock = new Object();

    private static class MyThread1 extends Thread {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("MyThread1 get lock...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("MyThread1 release lock...");
            }
        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("MyThread2 get lock...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("MyThread2 release lock...");
            }
        }
    }

    private static class MyThread3 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("MyThread3 get lock...");
                // lock.notify();
                lock.notifyAll();
                System.out.println("MyThread3 release lock...");
            }
        }
    }

    public static void main(String[] args) {
        new MyThread1().start();
        new MyThread2().start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new MyThread3().start();
    }

}
