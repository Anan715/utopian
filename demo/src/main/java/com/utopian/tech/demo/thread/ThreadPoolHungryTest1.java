package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程池饥饿现象解决
 */
@Slf4j
public class ThreadPoolHungryTest1 {

    static final List<String> MENU = Arrays.asList("地三鲜", "宫保鸡丁", "辣子鸡丁");
    static Random random = new Random();

    static String cooking() {
        return MENU.get(random.nextInt(MENU.size()));
    }

    public static void main(String[] args) {
        // 阿里推荐创建线程池的方式
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());


        ExecutorService waiterPool = Executors.newFixedThreadPool(1);
        ExecutorService cookPool = Executors.newFixedThreadPool(1);
        waiterPool.execute(() -> {

                    log.info("开始点菜");
                    Future<String> future = cookPool.submit(() -> {
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

        waiterPool.execute(() -> {

                    log.info("开始点菜");
                    Future<String> future = cookPool.submit(() -> {
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

        waiterPool.execute(() -> {

                    log.info("开始点菜");
                    Future<String> future = cookPool.submit(() -> {
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

        waiterPool.execute(() -> {

                    log.info("开始点菜");
                    Future<String> future = cookPool.submit(() -> {
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

        waiterPool.execute(() -> {

                    log.info("开始点菜");
                    Future<String> future = cookPool.submit(() -> {
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
