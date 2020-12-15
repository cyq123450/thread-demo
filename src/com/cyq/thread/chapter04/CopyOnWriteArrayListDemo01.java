package com.cyq.thread.chapter04;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList演示
 * CopyOnWriteArrayList中的迭代器有弱一致性，所谓的弱一致性指的是其他线程对该集合的增删改对迭代器是不可见的。
 */
public class CopyOnWriteArrayListDemo01 {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("hello");
        list.add("world");

        // 获取迭代器
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
