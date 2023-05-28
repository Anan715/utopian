package com.utopian.tech.demo.thread;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class InvokeAllTaskTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    Thread.sleep(500);
                    return Thread.currentThread().getName() + StringPool.COLON + System.currentTimeMillis();
                },

                () -> {
                    Thread.sleep(500);
                    return Thread.currentThread().getName() + StringPool.COLON + System.currentTimeMillis();
                },
                () -> {
                    Thread.sleep(500);
                    return Thread.currentThread().getName() + StringPool.COLON + System.currentTimeMillis();
                },
                () -> {
                    Thread.sleep(500);
                    return Thread.currentThread().getName() + StringPool.COLON + System.currentTimeMillis();
                }
        ));

        futures.stream().forEach(i -> {
            try {
                log.info(i.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }
}
