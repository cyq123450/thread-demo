package com.cyq.juc.demo02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据原子性演示。
 *      volatile只能保证数据的可见性，不能保证数据的原子性操作。java中的原子类对数据提供了原子性保证。
 *      原子类的底层原理：
 *          1.所有的变量都使用了volatile进行了修饰；
 *          2.采用了CAS(比较并赋值)算法保证数据的原子性。
 *              CAS算法并硬件对于并发操作共享数据的支持；
 *              CAS包含了三个操作数：
 *                  内存值V；预估值A；更新值B
 *                  当且仅当 V == A 时，V = B，否则什么都不做。
 */
public class AtomicDemo02 {

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        for(int i = 0; i < 10; i++) {
            new Thread(myThread1).start();
        }
    }

}

class MyThread1 implements Runnable {

    private AtomicInteger serialNum = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getSerialNum());
    }

    public int getSerialNum() {
        return serialNum.getAndIncrement();
    }

    public void setSerialNum(AtomicInteger serialNum) {
        this.serialNum = serialNum;
    }
}
