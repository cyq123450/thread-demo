package com.cyq.juc.demo08;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁演示。
 *      ReadWriteLock是读写锁，读读之间是共享的，写写和读写之间是互斥的。
 */
public class ReadWriteLockDemo01 {

    public static void main(String[] args) {

        Resources1 resources1 = new Resources1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                resources1.write();
            }
        }).start();

        for(int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    resources1.read();
                }
            }).start();
        }
    }

}

class Resources1 {

    private int shareNum = 0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        lock.readLock().lock();
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " --> 读：" + shareNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        lock.writeLock().lock();
        try {
            Thread.sleep(500);
            shareNum = (int)(Math.random() * 100);
            System.out.println(Thread.currentThread().getName() + " --> 写：" + shareNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

}
