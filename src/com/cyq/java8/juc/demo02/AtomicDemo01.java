package com.cyq.java8.juc.demo02;

/**
 * 原子性演示。
 *      volatile修饰变量只能保存数据的可见性，不能保证数据的原子性。
 */
public class AtomicDemo01 {

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        for (int i = 0; i < 10; i++) {
            new Thread(myThread).start();
        }
    }
}

class MyThread implements Runnable {

    private volatile int serialNum = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serialNum++;
        System.out.println(serialNum);
    }
}


