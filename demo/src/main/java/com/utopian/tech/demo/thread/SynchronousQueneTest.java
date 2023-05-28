package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;

@Slf4j
public class SynchronousQueneTest {

    public static void main(String[] args) {

        SynchronousQueue<Integer> quene = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                log.info("待存放1");
                quene.put(1);
                log.info("已存放1");

                Thread.sleep(500);

                log.info("待存放2");
                quene.put(2);
                log.info("已存放2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                log.info("取值ing......");
                Integer take = quene.take();
                log.info("已取值{}", take);

                Thread.sleep(500);

                log.info("取值ing......");
                Integer take2 = quene.take();
                log.info("已取值{}", take2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();




    }
}
