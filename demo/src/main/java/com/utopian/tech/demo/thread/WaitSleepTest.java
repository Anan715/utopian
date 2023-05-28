package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class WaitSleepTest {
    final static Object waitLock = new Object();
    final static Object sleepLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (waitLock) {
                log.info("wait debug............");
                try {
                    // 无限等待，当有参数时，等待对应的时间
                    waitLock.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("T1 other things.........");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (sleepLock) {
                log.info("T2 debug............");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("T2 other things.........");
            }
        }, "t2").start();
        sleep(200);

        synchronized (waitLock) {
            log.debug("获取wait锁");
        }
        synchronized (sleepLock) {
            log.debug("获取sleep锁");
        }
    }
}
