package com.cyq.chapter01.demo02;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *      一个线程调用了共享资源的wait()方法后会进入线程阻塞状态，并释放该共享资源的锁资源，当发生以下情况时，该线程才会返回：
 *          1.共享资源调用了notify()或notifyAll()方法；
 *          2.其他线程调用了该线程的interrupt()方法，则该线程会抛出InterruptdException异常返回。
 *      在调用对象的wait()方法前需要获取该对象的监视器锁，如果没有获取该对象的监视器锁会抛出IllegalMonitorStateException异常。
 *      一个对象可以通过执行共享变量的同步代码块或同步方法获取对象的监视器法锁。
 *      一个处于wait()状态的线程，除了调用notify()、notifyAll()、interrupt()或超时等待的情况被唤醒叫做虚假唤醒，为了防止线程发生虚假唤醒需要通过循环的条件判断。
 *
 * 总结：
 */
public class WaitAndNotifyDemo01 {

    // 共享资源
    private static List<String> queue = new ArrayList<>();

    /**
     * 生产者
     */
    public static class ProcedureTask extends Thread {
        @Override
        public void run() {
            synchronized (queue) {  // 获取对象监视器锁
                while (queue.size() > 0) {  // 防止线程虚假唤醒
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                queue.add("生产者生产商品");
                queue.notifyAll();  // 唤醒该资源的阻塞线程
                System.out.println("生产者已生产产品");

                try {
                    Thread.sleep(1000); // 模拟业务逻辑等待时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者
     */
    public static class CustomerTask extends Thread {

        @Override
        public void run() {
            synchronized (queue) {  // 获取对象监视器锁
                while (queue.size() <= 0) {    // 防止线程虚假唤醒
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                String result = queue.get(0);
                System.out.println("消费者消费产品：" + result);
                queue.notifyAll();

                try {
                    Thread.sleep(1000); // 模拟业务逻辑等待时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        new ProcedureTask().start();
        new CustomerTask().start();
    }

}
