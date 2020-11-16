package com.cyq.chapter02.demo01;

import sun.misc.Unsafe;

/**
 *  描述:
 *      由于Unsafe类是直接操作内存的，所以为了安全，JDK开发组对通过正规渠道获取Unsafe实例的方法做了限制。
 */
public class UnsafeDemo01 {

    static final Unsafe unsafe = Unsafe.getUnsafe();

    static final long stateOffset;

    private volatile long state = 0;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(UnsafeDemo01.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        UnsafeDemo01 unsafeDemo01 = new UnsafeDemo01();
        boolean ret = unsafe.compareAndSwapInt(unsafeDemo01, stateOffset, 0, 1);
        System.out.println("ret:" + ret);
    }

}
