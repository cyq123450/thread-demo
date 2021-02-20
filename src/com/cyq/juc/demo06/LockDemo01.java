package com.cyq.juc.demo06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock演示。
 *      解决线程安全问题的方法：
 *          synchronized关键字：同步方法；同步代码块。synchronized隐式加锁和解锁
 *          lock关键字：需要进行显式加锁(lock())和解锁(unlock())
 */
public class LockDemo01 {

    public static void main(String[] args) {
        MyThread1 thread = new MyThread1();
        new Thread(thread, "窗口一").start();
        new Thread(thread, "窗口二").start();
        new Thread(thread, "窗口三").start();
    }

}

class MyThread1 implements Runnable {

    private int ticketNum = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (ticketNum > 1) {
            if (ticketNum <= 0) {
                break;
            }
            lock.lock();
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "---售卖第" + ticketNum-- + "张车票");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}