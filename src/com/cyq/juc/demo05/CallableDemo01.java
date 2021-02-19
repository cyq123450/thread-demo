package com.cyq.juc.demo05;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable:
 *      实现Callable接口可以获取线程执行完成之后的返回值，并且可以抛出对应的异常信息。
 *      实现Callable接口需要通过FutureTask类来接收对应的返回值数据。
 *
 */
public class CallableDemo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread1 myThread1 = new MyThread1();
        FutureTask<Integer> task = new FutureTask<Integer>(myThread1);  // 使用FutureTask接口返回值
        new Thread(task).start();
        System.out.println("result --> " + task.get());

    }

}

class MyThread1 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}
