package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * reentrantLock
 * 可重入锁，
 */
@Slf4j
public class ReentrantLockTest {

    static final Object room = new Object();
    static Boolean hasCigaratte = false;
    static Boolean hasTakeOut = false;
    static ReentrantLock lock = new ReentrantLock();
    static Condition waitCigaratte = lock.newCondition();
    static Condition waitTakeOut = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            lock.lock();
            try {
                log.info("有钱没?{}", hasCigaratte);
                while (!hasCigaratte) {
                    log.info("没钱不干活");
                    try {
                        waitCigaratte.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("可以开工了");
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.info("有饭吗？");
                while (!hasTakeOut) {
                    log.info("没饭不干活");
                    try {
                        waitTakeOut.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("可以干活了");
            } finally {
                lock.unlock();
            }
        }, "t2").start();


        Thread.sleep(2000);
        new Thread(() -> {
            lock.lock();
            try {
                log.info("外卖来了");
                hasTakeOut = true;
                waitTakeOut.signalAll();
            } finally {
                lock.unlock();
            }
        }, "外卖闪送").start();

        Thread.sleep(2000);
        new Thread(() -> {
            lock.lock();
            try{
                log.info("烟来了");
                hasCigaratte = true;
                waitCigaratte.signalAll();
            }finally {
                lock.unlock();
            }
        }, "买烟的").start();
    }


}
