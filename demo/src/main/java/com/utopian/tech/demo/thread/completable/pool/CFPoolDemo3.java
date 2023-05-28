package com.utopian.tech.demo.thread.completable.pool;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.utopian.tech.demo.thread.completable.SmallTool;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class CFPoolDemo3 {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> foodQueue = new LinkedBlockingQueue<>(2);
        LinkedList<String> aList = new LinkedList<>();
        LinkedList<String> roadAList = new LinkedList<>();

        Thread aThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                String food = String.format("A厨师的第 %d 个食品", i);
                try {
                    Thread.sleep(4000);
                    foodQueue.put(food);
                    SmallTool.printTimeAndThread("A 厨师放入food：" + food);
                } catch (InterruptedException e) {
                    SmallTool.printTimeAndThread("A 厨师被中断：" + e.getMessage());
                }
                aList.add(String.format("%d A 厨师制作了 [%s]", System.currentTimeMillis(), food));
            }
        });

        Thread roadAThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {

                String food = null;
                try {
                    SmallTool.printTimeAndThread("路人开始买东西......");
                    // .poll(2, TimeUnit.SECONDS) 设置等待时间，该方法为 LinkedBlockingQueue 的方法，
                    // 其父类 Queue 有 poll()方法，相当于不等待直接获取
                    food = foodQueue.poll(2, TimeUnit.SECONDS);
                    SmallTool.printTimeAndThread("路人 A 获取：" + food);
                } catch (InterruptedException e) {
                    SmallTool.printTimeAndThread("路人 A 被中断：" + e.getMessage());
                }
                roadAList.add(String.format("%d 路人A买到了 [%s]", System.currentTimeMillis(), food));
            }
        });

        aThread.start();
        roadAThread.start();

        try {
            aThread.join();
            roadAThread.join();
        } catch (InterruptedException e) {
            SmallTool.printTimeAndThread(" join 被中断：" + e.getMessage());
        }
        log.info(aList.stream().collect(Collectors.joining(StringPool.COMMA)));
        log.info("#########################################################");
        log.info(roadAList.stream().collect(Collectors.joining("\n")));

        aList.forEach(System.out::println);
        aList.sort(Comparator.comparingInt(String::length));


    }
}
