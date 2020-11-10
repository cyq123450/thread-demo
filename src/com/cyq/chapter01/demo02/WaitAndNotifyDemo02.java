package com.cyq.chapter01.demo02;

/**
 * 描述：wait()只会释放当前变量占用的锁资源，并不会释放其他占用的共享变量的锁资源
 */
public class WaitAndNotifyDemo02 {

    private static Object lock1 = new Object(); // 锁1
    private static Object lock2 = new Object(); // 锁2

    public static class MyThread1 extends Thread {

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println(this.getName() + " ---> 获取lock1锁...");
                synchronized (lock2) {
                    System.out.println(this.getName() + " ---> 获取lock2锁...");
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class MyThread2 extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                System.out.println(this.getName() + " ---> 获取lock1锁...");
                synchronized (lock2) {
                    System.out.println(this.getName() + " ---> 获取lock2锁...");
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        myThread1.setName("线程1");
        MyThread2 myThread2 = new MyThread2();
        myThread2.setName("线程2");
        myThread1.start();
        myThread2.start();
    }

}
