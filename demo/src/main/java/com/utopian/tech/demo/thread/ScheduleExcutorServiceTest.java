package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleExcutorServiceTest {


    public static void main(String[] args) {
        // 以一定频率的执行某任务，
        // 参数：任务，初始延时时间，间隔时间，时间单位
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(() -> {
            log.info("running......");
        }, 1, 2, TimeUnit.SECONDS);


//        methodDelay();
    }

    // 延时执行任务线程池 delay
    // 延时任务线程池
    // 相较于Timer,此线程模式当某线程出现异常时，还会继续执行后续任务
    // timer当有线程出现异常时，则会终止向下执行
    private static void methodDelay() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.schedule(() -> {
            log.info(Thread.currentThread().getName() + 3);
        }, 3, TimeUnit.SECONDS);
        pool.schedule(() -> {
            log.info(Thread.currentThread().getName() + 2);
        }, 2, TimeUnit.SECONDS);
        pool.schedule(() -> {
            int i = 1/0;
            log.info(Thread.currentThread().getName() + 1);
        }, 1, TimeUnit.SECONDS);

        log.info(Thread.currentThread().getName());
    }
}
