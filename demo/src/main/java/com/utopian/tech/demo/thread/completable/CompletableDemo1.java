package com.utopian.tech.demo.thread.completable;

import java.util.concurrent.CompletableFuture;

public class CompletableDemo1 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点菜");

        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜。。。");
            SmallTool.sleep(500L);
            return "鱼香肉丝 准备好了";
            // thenCompose 需要掺入一个函数式接口FuncTion，传入参数T，返回R
            // 等待上一个任务的返回值，交给另外一个任务执行，有返回之后执行
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员打饭。。。");
            SmallTool.sleep(100L);
            return dish + "米饭";
//            return 100;
        }));

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format(" %s, 小白开吃", cf.join()));
    }
}
