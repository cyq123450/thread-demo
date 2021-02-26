package com.cyq.java8.juc.demo07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生产者与消费者案例演示。
 *      该种方式有问题，主要是在else出现的地方，出现的问题是生产与消费数据的个数没有达到理想值，程序该停止却没有停止。
 *      理论上应该是容器中数据满时，生产者等待，直到被唤醒时就说明容器中的数据有被消费的，紧接着就要生产一个数据；
 *      容器中没有数据时，消费者等待，直到被唤醒时就说明容器中有数据产生，紧接着就要消费一个数据。
 *      应该去掉else关键字。
 */
public class ExampleDemo01 {

    public static void main(String[] args) {
        Resource1 resource1 = new Resource1();
        new Thread(new Productor1(resource1), "生产者").start();
        new Thread(new Consumer1(resource1), "消费者").start();
    }

}

class Resource1 {

    private List<Integer> res = new ArrayList<>();

    public synchronized void get() {
        if (res.size() >= 3) {
            try {
                System.out.println(Thread.currentThread().getName() + " -->容器数据已满");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            int reVal = new Random().nextInt();
            res.add(reVal);
            System.out.println(Thread.currentThread().getName() + " -->生产数据：" + reVal);
            this.notify();
        }
    }

    public synchronized void sale() {
        if (res.size() <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " -->容器数据已清");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Integer resVal = res.remove(0);
            System.out.println(Thread.currentThread().getName() + " --> 消费数据：" + resVal);
            this.notify();
        }
    }

}

class Productor1 implements Runnable {

    private Resource1 resource1;

    public Productor1(Resource1 resource1) {
        this.resource1 = resource1;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource1.get();
        }
    }
}

class Consumer1 implements Runnable {

    private Resource1 resource1;

    public Consumer1(Resource1 resource1) {
        this.resource1 = resource1;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            resource1.sale();
        }
    }
}
