package com.utopian.tech.demo.thread.completable.pool;


import com.utopian.tech.demo.thread.completable.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CFPoolDemo1 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("开始点菜");
        long startTime = System.currentTimeMillis();
        ExecutorService pool = Executors.newCachedThreadPool();
        CompletableFuture[] dishes = IntStream.range(1, 2000)
                .mapToObj(i -> new Dish("菜" + i, 1))
                .map(dish -> CompletableFuture.runAsync(dish::make, pool))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(dishes).join();
        SmallTool.printTimeAndThread("菜做好了，开始上桌； " + (System.currentTimeMillis() - startTime));
    }
}
