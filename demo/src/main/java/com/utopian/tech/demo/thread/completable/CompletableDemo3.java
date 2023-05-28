package com.utopian.tech.demo.thread.completable;

import java.util.concurrent.CompletableFuture;

public class CompletableDemo3 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白车站准备回家");

        // 等待先完成的线程执行
        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700公交正在赶来");
            SmallTool.sleep(200L);
            return "700 公交到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("800公交正在赶来");
            SmallTool.sleep(100L);
            return "800 公交到了";
        }), firstBus -> {
            SmallTool.printTimeAndThread(firstBus);
            if(firstBus.startsWith("800")){
                throw new RuntimeException("发生故障");
            }
            return firstBus;
        }).exceptionally(e -> {
            SmallTool.printTimeAndThread(e.getMessage());
            SmallTool.printTimeAndThread("打车回家");
            return "坐车回家";
        });


        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format(" %s, 小白离开", bus.join()));
    }
}
