package com.cyq.thread.chpater05;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo01 {

    public static void main(String[] args) {
        LockSupport.unpark(Thread.currentThread());
        System.out.println("begin...");
        LockSupport.park();
        System.out.println("end...");
    }

}
