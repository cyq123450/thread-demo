package com.cyq.chapter01.demo06;

/**
 * 描述：
 *      长时间处于sleep()状态的线程如果不想等待太长可以调用该线程的interrupt()方法使处于sleep状态的线程抛出异常，强制退出。
 */
public class InterruptDemo02 {

    private static class MyThreadA extends Thread {
        @Override
        public void run() {
            System.out.println("MyThreadA before sleep...");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThreadA after sleep...");
        }
    }

    public static void main(String[] args) {
        MyThreadA myThreadA = new MyThreadA();
        myThreadA.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThreadA.interrupt();
        try {
            myThreadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyThreadA end...");


    }

}
