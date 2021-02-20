package com.cyq.juc.demo07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生产者与消费者案例演示。
 *      该案例是对上一个案例不足之处的改进，需要把if改为while，避免虚假唤醒问题。
 */
public class ExampleDemo03 {

    public static void main(String[] args) {
        Resource3 resource3 = new Resource3();
        new Thread(new Productor3(resource3), "生产者A").start();
        new Thread(new Consumer3(resource3), "消费者B").start();
        new Thread(new Productor3(resource3), "生产者A").start();
        new Thread(new Consumer3(resource3), "消费者B").start();
    }

}

class Resource3 {

    private List<Integer> res = new ArrayList<>();

    public synchronized void get() {
        while (res.size() >= 3) {
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
        while (res.size() <= 0) {
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

class Productor3 implements Runnable {

    private Resource3 resource3;

    public Productor3(Resource3 resource3) {
        this.resource3 = resource3;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource3.get();
        }
    }
}

class Consumer3 implements Runnable {

    private Resource3 resource3;

    public Consumer3(Resource3 resource3) {
        this.resource3 = resource3;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            resource3.sale();
        }
    }
}
