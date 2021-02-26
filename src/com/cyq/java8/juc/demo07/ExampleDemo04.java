package com.cyq.java8.juc.demo07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者与消费者案例演示。
 *      该案例使用同步锁的方式进行
 */
public class ExampleDemo04 {

    public static void main(String[] args) {
        Resource4 resource4 = new Resource4();
        new Thread(new Productor4(resource4), "生产者A").start();
        new Thread(new Consumer4(resource4), "消费者B").start();
        new Thread(new Productor4(resource4), "生产者A").start();
        new Thread(new Consumer4(resource4), "消费者B").start();
    }

}

class Resource4 {

    private List<Integer> res = new ArrayList<>();

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void get() {
        lock.lock();
        try {
            while (res.size() >= 3) {
                System.out.println(Thread.currentThread().getName() + " -->容器数据已满");
                condition.await();
            }
            int reVal = new Random().nextInt();
            res.add(reVal);
            System.out.println(Thread.currentThread().getName() + " -->生产数据：" + reVal);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sale() {
        lock.lock();
        try {
            while (res.size() <= 0) {
                System.out.println(Thread.currentThread().getName() + " -->容器数据已清");
                condition.await();
            }
            Integer resVal = res.remove(0);
            System.out.println(Thread.currentThread().getName() + " --> 消费数据：" + resVal);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

class Productor4 implements Runnable {

    private Resource4 resource4;

    public Productor4(Resource4 resource4) {
        this.resource4 = resource4;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource4.get();
        }
    }
}

class Consumer4 implements Runnable {

    private Resource4 resource4;

    public Consumer4(Resource4 resource4) {
        this.resource4 = resource4;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            resource4.sale();
        }
    }
}
