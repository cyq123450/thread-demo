package com.cyq.thread.chapter01.demo08;

/**
 * 描述：
 *      Java中的线程分为用户线程和守护线程。
 *      守护线程是不会响应JVM停止的线程，而用户线程会响应JVM的停止，只要JVM中还存在用户线程，则JVM就不会停止；而守护线程不会响应JVM的停止时间。
 */
public class DemonDemo01 {

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            while (true) {}
        }
    }

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        myThread1.setDaemon(true);  // 设置为守护线程
        myThread1.start();
        System.out.println("main thread end...");
    }

}
