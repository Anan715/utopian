package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class WaitNotifyTest {
    final static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (obj) {
                log.info("T1 debug............");
                try {
                    // 无限等待，当有参数时，等待对应的时间
                    obj.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("T1 other things.........");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (obj) {
                log.info("T2 debug............");
                try {
                    obj.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("T2 other things.........");
            }
        }, "t2").start();
        sleep(2000);
        log.debug("唤醒 wait 状态的线程");
        synchronized (obj) {
//            obj.notify();
            obj.notifyAll();
        }
    }
}
