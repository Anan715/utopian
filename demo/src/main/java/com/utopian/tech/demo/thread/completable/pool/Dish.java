package com.utopian.tech.demo.thread.completable.pool;


import com.utopian.tech.demo.thread.completable.SmallTool;

public class Dish {
    private String name;
    private Integer productTime;

    public Dish(String name, Integer productTime) {
        this.name = name;
        this.productTime = productTime;
    }

    public void make(){
        SmallTool.sleep(1000L);
        Thread.interrupted();
        Thread.currentThread().isInterrupted();
        SmallTool.printTimeAndThread(this.name + "制作完成");
    }
}
