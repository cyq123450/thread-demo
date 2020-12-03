package com.cyq.thread.chapter01.demo02;

/**
 * 描述：当线程调用了wait()方法后，调用该线程的interrupt()会抛出异常并返回
 */
public class WaitAndNotifyDemo03 {

    private static Object lock = new Object();

    public static class MyThread extends Thread {

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println(this.getName() + " ---> 进入wait状态...");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.interrupt();
    }

}
