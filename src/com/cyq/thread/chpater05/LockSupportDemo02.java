package com.cyq.thread.chpater05;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo02 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                System.out.println("park...");
                LockSupport.park();
                System.out.println("park2...");
            }
        });
        thread.start();

        Thread.sleep(1000);

        LockSupport.unpark(thread);
        // thread.interrupt();
        System.out.println("unpark..");

    }

}
