package com.utopian.tech.demo.thread.completable;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点菜");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜。。。");
            SmallTool.sleep(500L);
            return "鱼香肉丝";
            // thenCombine需要传入两个参数，一个是异步任务，此任务和第一个任务同时开启
            // 另一个是BiFunction函数，该函数是把两个值合并为一个
        }).thenCombine(CompletableFuture.supplyAsync(() -> { // 异步任务
            SmallTool.printTimeAndThread("服务员煮饭。。。");
            SmallTool.sleep(500L);
            return "米饭";
        }), (dish, rice) -> { // BiFunction函数
            SmallTool.printTimeAndThread("服务员打饭。。。");
            SmallTool.sleep(500L);
            return String.format("%s + %s 好了", dish, rice);
        });

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format(" %s, 小白开吃", cf.join()));

    }
}
