package com.cyq.java8.juc.demo03;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  CopyOnWriteArrayList/CopyOnWriteArraySet:"写入并复制"
 *  注意：
 *      添加操作多时，效率低，因为每次添加都会进行复制，开销非常大。并发操作迭代多时可以选择。
 */
public class ConcurrentDemo01 {

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        for (int i = 0; i < 10; i++) {
            new Thread(myThread1).start();
        }
    }

}

class MyThread1 implements Runnable {

    /**
     * 该方式会产生异常。java.util.ConcurrentModificationException
     */
    // private static List list1 = new ArrayList();

    /**
     * 该方式会产生异常。java.util.ConcurrentModificationException
     */
    // private static List list1 = Collections.synchronizedList(new ArrayList<>());

    /**
     * 该方式不会产生异常。
     */
    private static List list1 = new CopyOnWriteArrayList();

    static {
        list1.add("1");
        list1.add("2");
    }

    @Override
    public void run() {
        Iterator iterator = list1.iterator();
        while (iterator.hasNext()) {
            System.out.println(Thread.currentThread().getName() + " ---> " + iterator.next());
            list1.add("A");
        }
    }
}
