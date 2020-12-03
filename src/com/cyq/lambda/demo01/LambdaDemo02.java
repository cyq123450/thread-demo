package com.cyq.lambda.demo01;

public class LambdaDemo02 {

    public static void main(String[] args) {

        // 1.创建匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1...");
            }
        }).start();

        // 2.使用lambda创建匿名匿名内部类
        new Thread(() -> System.out.println("2...")).start();

        // 3.创建匿名内部类
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("3...");
            }
        };

        // 4.使用lambda创建匿名匿名内部类
        Runnable r2 = () -> System.out.println("4...");

        r1.run();
        r2.run();

    }

}
