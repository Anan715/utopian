package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * 各个线程实现顺序输出 3 -2 -1 park un_park 实现方式
 */
@Slf4j
public class ThreadOnSequenceTest2 {


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.info("t1 ----- 1");
        }, "t1");


        t1.start();
        Thread t2 = new Thread(() -> {
            LockSupport.park();
            log.info("t2 ----- 2");
            LockSupport.unpark(t1);
        }, "t2");

        t2.start();
        Thread t3 = new Thread(() -> {
            log.info("t3 ----- 3");
            LockSupport.unpark(t2);
        }, "t3");

        t3.start();
    }


}
