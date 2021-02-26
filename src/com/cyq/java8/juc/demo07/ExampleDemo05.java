package com.cyq.java8.juc.demo07;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程按序交替执行案例。
 *
 */
public class ExampleDemo05 {

    public static void main(String[] args) {
        Resources5 resources5 = new Resources5();
        new Thread(new ThreadA(resources5), "ThreadA").start();
        new Thread(new ThreadB(resources5), "ThreadB").start();
        new Thread(new ThreadC(resources5), "ThreadC").start();
    }

}

class Resources5 {

    private volatile int num = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void getA() {
        lock.lock();
        try {
            for(int i = 0; i < 5; i++) {
                while (num != 1) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + " --> A");
                num = 2;
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void getB() {
        lock.lock();
        try {
            for(int i = 0; i < 5; i++) {
                while (num != 2) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + " --> B");
                num = 3;
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void getC() {
        lock.lock();
        try {
            for(int i = 0; i < 5; i++) {
                while (num != 3) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + " --> C");
                num = 1;
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

class ThreadA implements Runnable {

    private Resources5 resources5;

    public ThreadA(Resources5 resources5) {
        this.resources5 = resources5;
    }

    @Override
    public void run() {
        resources5.getA();
    }
}

class ThreadB implements Runnable {

    private Resources5 resources5;

    public ThreadB(Resources5 resources5) {
        this.resources5 = resources5;
    }

    @Override
    public void run() {
        resources5.getB();
    }
}

class ThreadC implements Runnable {

    private Resources5 resources5;

    public ThreadC(Resources5 resources5) {
        this.resources5 = resources5;
    }

    @Override
    public void run() {
        resources5.getC();
    }
}
