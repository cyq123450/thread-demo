package com.cyq.chapter01.demo02;

/**
 * 总结：
 *      notifyAll()方法的执行会使所有线程在该资源的wait()唤醒。
 *      调用共享变量的notifyAll()方法只会唤醒在该方法之前调用wait()的线程，在该方法之后调用wait()的线程将不会被唤醒。
 * notify()和notifyAll()对比：
 *      notify()只会唤醒一个线程，而notifyAll()会唤醒全部的线程。
 */
public class WaitAndNotifyDemo06 {

    private static volatile Object resource = new Object();

    private static class MyThreadA extends Thread {
        @Override
        public void run() {
            synchronized (resource) {
                System.out.println("MyThreadA get resource lock...");
                try {
                    System.out.println("MyThreadA begin wait...");
                    resource.wait();
                    System.out.println("MyThreadA end wait...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class MyThreadB extends Thread {
        @Override
        public void run() {
            synchronized (resource) {
                synchronized (resource) {
                    System.out.println("MyThreadB get resource lock...");
                    try {
                        System.out.println("MyThreadB begin wait...");
                        resource.wait();
                        System.out.println("MyThreadB end wait...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class MyThreadC extends Thread {
        @Override
        public void run() {
            synchronized (resource) {
                System.out.println("MyThreadC get resource lock...");
                System.out.println("MyThreadC begin notifyAll...");
                // resource.notify();
                resource.notifyAll();
                System.out.println("MyThreadC end notifyAll...");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadA myThreadA = new MyThreadA();
        MyThreadB myThreadB = new MyThreadB();
        MyThreadC myThreadC = new MyThreadC();
        myThreadA.start();
        myThreadB.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThreadC.start();

        // 等待三个线程执行
        myThreadA.join();
        myThreadB.join();
        myThreadC.join();

        System.out.println("主线程执行结束...");
    }


}
