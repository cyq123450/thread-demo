package com.cyq.chapter01.demo01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 描述：创建线程的三种方式。
 *      1.继承Thread类并重写run方法
 *      2.实现Runnable接口并重写run方法
 *      3.使用FutrueTask方式
 * 总结：
 *     Java中支持单继承多实现，继承Thread类就不能继承其他类了，继承Thread类可以在本类中通过this使用该线程；
 *     实现Runnable接口的线程不仅可以继承其他类，而且还可以实现其他接口，但在该类中不能直接通过this使用该线程，只能通过Thread.currentThread()方法获取该线程；
 *     无论是继承Thread类还是实现Runnable接口都不能获取到线程执行后返回的结果值，实现Callable接口，通过FutureTask的方式可以获取到线程执行后的返回值。
 *
 */
public class ThreadCreateMethodDemo {

    /**
     * 继承Thread类创建线程
     */
    public static class ThreadTask extends Thread {
        @Override
        public void run() {
            System.out.println("继承Thread类创建线程...");
        }
    }

    /**
     * 实现Runnable接口
     */
    public static class RunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println("实现Runnable接口...");
        }
    }

    /**
     * 实现Callable接口
     */
    public static class CallableTask implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println("实现Callable接口的方式...");
            return "Callable";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 继承Thread类方式
        new ThreadTask().start();

        // 实现Runnable接口的方式
        new Thread(new RunnableTask()).start();

        // 使用FutureTask的方式
        FutureTask<String> futureTask = new FutureTask<String>(new CallableTask());
        new Thread(futureTask).start();
        String retVal = futureTask.get();   // 等待线程执行结束后返回的结果数据
        System.out.println("FutureTask return value:" + retVal);
    }


}
