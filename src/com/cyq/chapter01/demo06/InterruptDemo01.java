package com.cyq.chapter01.demo06;

/**
 * 描述：
 *      isInterrupted()：判断当前线程是否处于中断状态。
 *      interrupt()：给线程设置中断标志位，该方法只是给线程设置一个标记，并不能中断线程的执行。interrupt()方法会使处于wait()、sleep()、join()的线程抛出异常。
 *      interrupted()：判断当前线程是否处于中断状态，如果处于中断状态会清除该中断标记。
 */
public class InterruptDemo01 {

    private static class MyThreadA extends Thread {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " ---> in run()...");
            }
            System.out.println(Thread.currentThread().getName() + " ---> end run()...");
        }
    }

    public static void main(String[] args) {
        MyThreadA myThreadA = new MyThreadA();
        myThreadA.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThreadA.interrupt();
        try {
            myThreadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main end...");

    }

}
