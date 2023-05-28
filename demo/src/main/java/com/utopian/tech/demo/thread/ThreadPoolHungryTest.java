package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池饥饿现象
 */
@Slf4j
public class ThreadPoolHungryTest {

    static final List<String> MENU = Arrays.asList("地三鲜", "宫保鸡丁", "辣子鸡丁");
    static Random random = new Random();

    static String cooking() {
        return MENU.get(random.nextInt(MENU.size()));
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(() -> {

                    log.info("开始点菜");
                    Future<String> future = pool.submit(() -> {
                        log.info("开始做菜");
                        return cooking();
                    });

                    try {
                        log.info("上菜：{}", future.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );

        pool.execute(() -> {

                    log.info("开始点菜");
                    Future<String> future = pool.submit(() -> {
                        log.info("开始做菜");
                        return cooking();
                    });

                    try {
                        log.info("上菜：{}", future.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );

    }
}
