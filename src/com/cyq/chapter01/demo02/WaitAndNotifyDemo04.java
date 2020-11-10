package com.cyq.chapter01.demo02;

/**
 * 总结：
 *      wait(long timeout):调用对象的wait方法后，如果在timeout时间内内唤醒，该线程会停止阻塞状态；如果在timeout时间内没有被唤醒，该线程也会停止阻塞状态。
 *      wait(long timeout, int nanos):如果nanos在(0, 999999)时，timeout会进行加1。
 */
public class WaitAndNotifyDemo04 {

    private static Object lock = new Object();

    /**
     * 演示wait(long timeout)
     */
    public static class MyThread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println("MyThread1 ---> 开始进入wait()...");
                    lock.wait(2000);
                    System.out.println("MyThread1 ---> 已超时...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("MyThread2 ---> 开始进入wait()...");
                try {
                    lock.wait(2000, 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("MyThread2 ---> 已超时退出...");
            }
        }
    }

    public static void main(String[] args) {
        // new MyThread1().start();
        new MyThread2().start();
    }

}
