package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 提交任务task,Future返回值取值demo
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<String> future = pool.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                log.debug("running");
                Thread.sleep(500);
                log.info("线程池准备返回结果");
                return "ok";
            }
        });
        log.info("主线程接收结果{}",future.get());
    }
}


