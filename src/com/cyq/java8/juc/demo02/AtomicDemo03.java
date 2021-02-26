package com.cyq.java8.juc.demo02;

/**
 * 自定义原子类演示。
 *
 */
public class AtomicDemo03 {

    public static void main(String[] args) {
        final AtomicDemo03 cas = new AtomicDemo03();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int i1 = cas.get();
                    boolean b = cas.compareAndSet(i1, cas.get() + 1);
                    System.out.println(b + " --> " + i1);

                    /*
                    int num = cas.getAndIncrement();
                    System.out.println(num);
                    */

                }
            }).start();
        }
    }

    private volatile int value = 0;

    public synchronized int get() {
        return this.value;
    }

    public synchronized int compareAndSwap(int exceptedValue, int newValue) {
        int oldValue = this.value;
        if (oldValue == exceptedValue) {
            this.value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int exceptedValue, int newValue) {
        return exceptedValue == compareAndSwap(exceptedValue, newValue);
    }

    public synchronized int getAndIncrement() {
        int oldValue = this.value;
        boolean retVal = compareAndSet(oldValue, 1 + oldValue);
        while (!retVal) {
            oldValue = this.value;
            retVal = compareAndSet(oldValue, 1 + oldValue);
        }
        return oldValue;
    }

}

