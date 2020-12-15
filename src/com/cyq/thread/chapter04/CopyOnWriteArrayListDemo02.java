package com.cyq.thread.chapter04;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList中的迭代器具有弱一致性。因为该迭代器使用的是数组的一个快照。
 */
public class CopyOnWriteArrayListDemo02 {

    private static volatile CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("hello");
        list.add("beijing");
        list.add("welcome");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                list.add("test");
                list.remove(1);
            }
        });

        Iterator<String> iterator = list.iterator();

        t1.start();
        t1.join();

        System.out.println("1:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ", ");
        }
        System.out.println();

        iterator = list.iterator();

        System.out.println(":2：");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ", ");
        }
    }

}
