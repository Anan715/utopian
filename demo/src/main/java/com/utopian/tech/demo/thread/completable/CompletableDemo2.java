package com.utopian.tech.demo.thread.completable;

import java.util.concurrent.CompletableFuture;

public class CompletableDemo2 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白结账");

        // 两个线程收费 + 开票 原始写法
//        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
//            SmallTool.printTimeAndThread("服务员收款500");
//            SmallTool.sleep(1000L);
//            CompletableFuture<String> waiter = CompletableFuture.supplyAsync(() -> {
//                SmallTool.printTimeAndThread("服务员开发票500");
//                SmallTool.sleep(1000L);
//                return "发票500";
//            });
//            return waiter.join();
//        });

        // 单线程开票收费
//        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
//            SmallTool.printTimeAndThread("服务员收款500");
//            SmallTool.sleep(1000L);
//            return 500;
//        }).thenApply(money -> {
//            SmallTool.printTimeAndThread("服务员开发票500");
//            SmallTool.sleep(1000L);
//            return String.format("%s 元发票开好了", money);
//        });


        // 两个线程开票 + 收费
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员收款500");
            SmallTool.sleep(1000L);
            return 500;
        }).thenApplyAsync(money -> {
            SmallTool.printTimeAndThread("服务员开发票500");
            SmallTool.sleep(1000L);
            return String.format("%s 元发票开好了", money);
        });


        // 两个线程开票 + 收费
//        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
//            SmallTool.printTimeAndThread("服务员收款500");
//            SmallTool.sleep(1000L);
//            return 500;
//        }).thenCompose(money -> CompletableFuture.supplyAsync(() -> {
//            SmallTool.printTimeAndThread("服务员开发票500");
//            SmallTool.sleep(1000L);
//            return String.format("%s 元发票开好了", money);
//        }));

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format(" %s, 小白离开", cf.join()));
    }
}
