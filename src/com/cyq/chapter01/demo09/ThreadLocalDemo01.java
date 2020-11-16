package com.cyq.chapter01.demo09;

/**
 * 描述:
 *      ThreadLocal是每个线程单独在内存中的一个副本。
 */
public class ThreadLocalDemo01 {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static void print(String str) {
        System.out.println(str + " ---> " + threadLocal.get());
        threadLocal.remove();
    }

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            threadLocal.set("111111");
            print("MyThread1");
            System.out.println("MyThread1 ---> " + threadLocal.get());
        }
    }

    private static class MyThread2 extends Thread {

        @Override
        public void run() {
            threadLocal.set("222222");
            print("MyThread2");
            System.out.println("MyThread2 ---> " + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        new MyThread1().start();
        new MyThread2().start();
    }

}
