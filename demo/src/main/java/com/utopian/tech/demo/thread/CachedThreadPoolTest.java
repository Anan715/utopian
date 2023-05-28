package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CachedThreadPoolTest {
    public static void main(String[] args) {

        // 此线程池创建，无核心线程，自由应急线程，应急线程的存活时间为60秒
        // 队列·采用的是：SynchronousQueue<Runnable>，队列的大小为0，
        // 有线程取值时，才存入任务，类似于任务中转，但是不存储任务
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            pool.execute(() -> {
                log.info(Thread.currentThread().getName());
            });
        }
    }
}
