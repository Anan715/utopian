package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 各个线程实现顺序输出 3 -2 -1 wait-notify 实现方式
 */
@Slf4j
public class ThreadOnSequenceTest1 {

    static Boolean T2_RUNED = false;
    static Boolean T3_RUNED = false;
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            // synchronized使执行变为有先后顺序
            synchronized (lock) {
                while (!T2_RUNED) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                log.info("t1线程输出： 1");
            }
        }, "t1").start();


        new Thread(() -> {
            synchronized (lock) {
                while (!T3_RUNED) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("t2线程输出： 2");
                T2_RUNED = true;
                lock.notify();
            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (lock) {
                log.info("t3线程输出： 3");
                T3_RUNED = true;
                lock.notifyAll();
            }
        }, "t3").start();

    }


}
