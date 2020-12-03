package com.cyq.thread.chapter01.demo03;

/**
 * 描述：
 *      join()方法是Thread线程中的方法，调用该方法的线程需要执行完之后再执行其余的代码。
 */
public class JoinDemo01 {

    private static class MyThreadA extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThreadA execute end...");
        }
    }

    private static class MyThreadB extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThreadB execute end...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadA myThreadA = new MyThreadA();
        MyThreadB myThreadB = new MyThreadB();
        myThreadA.start();
        myThreadB.start();

        // 等待线程执行
        myThreadA.join();
        myThreadB.join();

        System.out.println("主线程 execute end...");
    }

}
