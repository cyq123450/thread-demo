package com.cyq.lambda.demo03;

/**
 * 创建匿名内部类
 */
public class LambdaDemo02 {

    public static void main(String[] args) {
        // 1.常用线程启动方式
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("1.常用线程启动方式...");
                    }
                }
        ).start();

        // 2.使用Lambda创建匿名内部类
    }

}
