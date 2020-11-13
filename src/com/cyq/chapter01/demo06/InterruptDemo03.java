package com.cyq.chapter01.demo06;

/**
 *  描述：
 *      interrupt():使线程处于中断状态，该方法不会使线程发生中断，只是设置了一个中断标志位。
 *      isInterruptd():判断线程是否处于中断状态。
 *      interrupted():判断当前线程是否处于中断状态，如果处于中断状态则将该中断标记清除。
 */
public class InterruptDemo03 {

    private static class MyThreadA extends Thread {

        @Override
        public void run() {
            while (true) {

            }
        }
    }

    public static void main(String[] args) {
        MyThreadA myThreadA = new MyThreadA();
        myThreadA.start();
        myThreadA.interrupt();
        System.out.println("myThreadA.isInterrupted:" + myThreadA.isInterrupted());
        System.out.println("myThreadA.interrupted():" + myThreadA.interrupted());
        System.out.println("Thread.interrupted():" + Thread.interrupted());
        System.out.println("Thread.currentThread().isInterrupted():" + Thread.currentThread().isInterrupted());
    }

}
