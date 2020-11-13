package com.cyq.chapter01.demo05;

/**
 * 描述：
 *      调用yield()方法会让该线程让出CPU的执行，转而就绪状态。
 *      CPU会为每一个线程分配一个时间片用于执行程序，当执行的程序调用yield()方法时，会让出CPU的执行时间片，转而进入就绪态，CPU会重新从线程中选择优先级较高的执行。
 * sleep()和yield()对比：
 *      sleep()会使线程进入阻塞挂起状态，在睡眠的这段时间并不会释放所持有的资源，并且CPU也不会调度该线程；
 *      yield()会使线程暂时放弃CPU资源，转而进入就绪状态继续抢占CPU，也有可能刚刚放弃就抢占到。
 */
public class YieldDemo01 {

    private static class MyThread implements Runnable {

        public MyThread() {
            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " ---> start...");
            Thread.yield();
            System.out.println(Thread.currentThread().getName() + " ---> end...");
        }
    }

    public static void main(String[] args) {
        new MyThread();
        new MyThread();
        new MyThread();
    }

}
