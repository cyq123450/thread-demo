package com.cyq.chapter02.demo01;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 *  描述：
 *      利用Unsafe类不能通过正规渠道获取Unsafe实例，但可以通过反射的方式创建该类的实例。
 */
public class UnsafeDemo02 {

    private static final Unsafe unsafe;

    private static final long stateOffset;

    private volatile long state = 0;

    static {
        try {
            Field fileld = Unsafe.class.getDeclaredField("theUnsafe");
            fileld.setAccessible(true);
            unsafe = (Unsafe)fileld.get(null);
            stateOffset = unsafe.objectFieldOffset(UnsafeDemo02.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        UnsafeDemo02 unsafeDemo02 = new UnsafeDemo02();
        boolean ret = unsafe.compareAndSwapInt(unsafeDemo02, stateOffset, 0, 1);
        System.out.println("ret ---> " + ret);
    }

}
