package com.cyq.java8.juc.demo01;

import org.junit.Test;

/**
 * 演示volatile解决内存可见性问题。
 *      对于多个线程访问同一个共享数据时，该共享数据会在主内存中存在一份，并且每个单独的线程中会保存该共享数据的一个副本。
 *      多个线程对同一个共享数据进行操作时，会产生彼此之间不可见问题，这就是内存不可见性。
 *      volatile可以保证多个线程操作共享数据时可以保证各线程之间是彼此可见的。
 *
 * volatile的缺点：
 *      volatile可以保证共享数据的内存可见性问题，但是volatile不会使JVM对java代码进行指令重排序，这样会导致程序执行效率的低下。
 *
 * volatile与synchronized区别：
 *      1.volatile是相比synchronized是一种轻量级的同步策略解决方案；
 *      2.volatile不具备“互斥性”，synchronized具有“互斥性”；
 *      3.volatile不能保证变量的原子性，synchronized可以保证变量的原子性。
 */
public class VolatileDemo01 {

    @Test
    public void test1() {
        MyThread myThread = new MyThread();
        myThread.start();

        /**
         * 由于while(true) 底层执行的非常快，所有会导致线程使用自己单独内存中的一些数据，会产生内存不可见性问题
         */
        while (true) {
            if(myThread.isFalg()) {
                System.out.println("测试线程体...");
                myThread.setFalg(false);
            }
        }
    }

    @Test
    public void test2() {
        MyThread myThread = new MyThread();
        myThread.start();
        while (true) {
            /**
             * 使用同步代码块可以解决内存不可见性问题，可以保证线程及时刷新最新数据。
             * 但是使用同步代码块会存在锁竞争问题，会降低执行效率。
             */
            synchronized (myThread) {
                if(myThread.isFalg()) {
                    System.out.println("测试线程体...");
                    myThread.setFalg(false);
                }
            }

        }
    }

    @Test
    public void test3() {
        MyThread myThread = new MyThread();
        myThread.start();
        while (true) {
            if(myThread.isFalgTmp()) {
                System.out.println("测试线程体...");
                myThread.setFalgTmp(false);
            }
        }
    }

    private class MyThread extends Thread {

        private boolean falg = false;

        /**
         * 使用volatile关键字可以解决内存不可见性问题
         */
        private volatile boolean falgTmp = false;

        @Override
        public void run() {
            try {
                Thread.sleep(200);
                falg = true;
                System.out.println("flag已被修改为:" + falg);
                falgTmp = true;
                System.out.println("falgTmp已被修改为:" + falg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public boolean isFalg() {
            return falg;
        }

        public void setFalg(boolean falg) {
            this.falg = falg;
        }

        public boolean isFalgTmp() {
            return falgTmp;
        }

        public void setFalgTmp(boolean falgTmp) {
            this.falgTmp = falgTmp;
        }
    }
}
