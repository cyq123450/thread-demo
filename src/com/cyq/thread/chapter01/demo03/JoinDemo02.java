package com.cyq.thread.chapter01.demo03;

/**
 *  描述：
 *      正在执行join()方法的线程如果该线程调用了interrupt()方法则会抛出异常。
 */
public class JoinDemo02 {

    private static class MyThreadA extends Thread {
        @Override
        public void run() {
            System.out.println("MyThreadA begin...");
            while(true) {

            }
        }
    }

    private static class MyThreadB extends Thread {

        private final Thread thread;

        public MyThreadB(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt();
        }
    }

    public static void main(String[] args) {
        MyThreadA myThreadA = new MyThreadA();
        Thread thread = Thread.currentThread();
        MyThreadB myThreadB = new MyThreadB(thread);

        myThreadA.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThreadB.start();
        try {
            myThreadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
