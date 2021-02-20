package com.cyq.juc.demo07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生产者与消费者案例演示。
 *      该方式是对上一个案例的改进，但仍然有不足之处，现在是一个生产者和一个消费者，运行起来是没有问题的，
 *      但是如果有多个生产者与消费者的话就会出现虚假唤醒问题。
 */
public class ExampleDemo02 {

    public static void main(String[] args) {
        Resource2 resource2 = new Resource2();
        new Thread(new Productor2(resource2), "生产者").start();
        new Thread(new Consumer2(resource2), "消费者").start();
    }

}

class Resource2 {

    private List<Integer> res = new ArrayList<>();

    public synchronized void get() {
        if (res.size() >= 3) {
            try {
                System.out.println(Thread.currentThread().getName() + " -->容器数据已满");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int reVal = new Random().nextInt();
        res.add(reVal);
        System.out.println(Thread.currentThread().getName() + " -->生产数据：" + reVal);
        this.notify();

    }

    public synchronized void sale() {
        if (res.size() <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " -->容器数据已清");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer resVal = res.remove(0);
        System.out.println(Thread.currentThread().getName() + " --> 消费数据：" + resVal);
        this.notify();
    }

}

class Productor2 implements Runnable {

    private Resource2 resource2;

    public Productor2(Resource2 resource2) {
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource2.get();
        }
    }
}

class Consumer2 implements Runnable {

    private Resource2 resource2;

    public Consumer2(Resource2 resource2) {
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            resource2.sale();
        }
    }
}
