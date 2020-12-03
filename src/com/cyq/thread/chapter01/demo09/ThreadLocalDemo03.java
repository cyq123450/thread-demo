package com.cyq.thread.chapter01.demo09;

/**
 *  描述：
 *      InheritableThreadLocal支持继承关系，父线程设置的值可以在子线程中获取到。
 *      InheritableThreadLocal的原理是父线程创建子线程时，子线程在创建构造方法时会复制父线程中的InheritableThreadLocal到子线程中。
 */
public class ThreadLocalDemo03 {

    private static InheritableThreadLocal<String> local = new InheritableThreadLocal<>();

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
