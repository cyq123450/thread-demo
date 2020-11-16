package com.cyq.chapter01.demo09;

/**
 * 描述：
 *      ThreadLocal不支持继承关系，即父线程在ThreadLocal变量中设置的值，子线程中无法获取到父线程中设置的值。
 */
public class ThreadLocalDemo02 {

    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {
        local.set("Hello World");
        System.out.println("主线程 ---> " + local.get());
        new Thread(){
            @Override
            public void run() {
                System.out.println("子线程 ---> " + local.get());
            }
        }.start();
    }

}
