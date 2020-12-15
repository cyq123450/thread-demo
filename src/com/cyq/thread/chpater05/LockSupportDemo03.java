package com.cyq.thread.chpater05;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo03 {

    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();

    public void lock() {
        boolean isInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);

        while (waiters.peek() != current || locked.compareAndSet(false, true)) {
            LockSupport.park(this);
            if (Thread.interrupted()) {
                isInterrupted = true;
            }
        }

        waiters.remove();
        if (isInterrupted) {
            current.interrupt();
        }

    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }

}
