package com.cyq.thread.chapter01.demo04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：
 *      调用线程的sleep()方法会让该线程进入睡眠状态，在该期间该线程不会去抢占CPU资源，但该线程持有的锁却不会释放。
 *      处于sleep()状态的线程在调用到了interrupt()方法时会抛出异常。
 */
public class SleepDemo01 {

    private static final Lock lock = new ReentrantLock();

    private static class MyThreadA extends Thread {

        @Override
        public void run() {
            lock.lock();
            System.out.println("MyThreadA get lock...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThreadA release lock...");
            lock.unlock();
        }
    }

    private static class MyThreadB extends Thread {

        @Override
        public void run() {
            lock.lock();
            System.out.println("MyThreadB get lock...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThreadB release lock...");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyThreadA myThreadA = new MyThreadA();
        MyThreadB myThreadB = new MyThreadB();
        myThreadA.start();
        myThreadB.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThreadA.interrupt();

    }

}
