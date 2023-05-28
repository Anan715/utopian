package com.utopian.tech.demo.thread.completable.pool;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.utopian.tech.demo.thread.completable.SmallTool;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
public class CFPoolDemo2 {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> foodQueue = new LinkedBlockingQueue<>(3);
        LinkedList<String> aList = new LinkedList<>();
        LinkedList<String> bList = new LinkedList<>();
        LinkedList<String> roadAList = new LinkedList<>();
        LinkedList<String> roadBList = new LinkedList<>();

        Thread aThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                String food = String.format("A厨师的第 %d 个食品", i);
                try {
                    foodQueue.put(food);
                    SmallTool.printTimeAndThread("A 厨师放入food：" + food);
                } catch (InterruptedException e) {
                    SmallTool.printTimeAndThread("A 厨师被中断：" + e.getMessage());
                }
                aList.add(String.format("%d A 厨师制作了 [%s]", System.currentTimeMillis(), food));
            }
        });

        Thread bThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                String food = String.format("B厨师的第 %d 个食品", i);
                try {
                    foodQueue.put(food);
                    SmallTool.printTimeAndThread("B 厨师放入food：" + food);
                } catch (InterruptedException e) {
                    SmallTool.printTimeAndThread("B 厨师被中断：" + e.getMessage());
                }
                bList.add(String.format("%d B 厨师制作了 [%s]", System.currentTimeMillis(), food));
            }
        });

        Thread roadAThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {

                String food = null;
                try {
                    // take 方法会一直阻塞获取，直到获取到数据，poll 可以设置等待时间
                    food = foodQueue.take();
                    SmallTool.printTimeAndThread("路人 A 获取：" + food);
                } catch (InterruptedException e) {
                    SmallTool.printTimeAndThread("路人 A 被中断：" + e.getMessage());
                }
                roadAList.add(String.format("%d 路人A买到了 [%s]", System.currentTimeMillis(), food));
            }
        });

        Thread roadBThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                String food = null;
                try {
                    food = foodQueue.take();
                    SmallTool.printTimeAndThread("路人 B 获取：" + food);
                } catch (InterruptedException e) {
                    SmallTool.printTimeAndThread("路人 B 被中断：" + e.getMessage());
                }
                roadBList.add(String.format("%d 路人 B 买到了 [%s]", System.currentTimeMillis(), food));
            }
        });

        aThread.start();
        bThread.start();
        roadAThread.start();
        roadBThread.start();

        try {
            aThread.join();
            bThread.join();
            roadAThread.join();
            roadBThread.join();
        } catch (InterruptedException e) {
            SmallTool.printTimeAndThread(" join 被中断：" + e.getMessage());
        }

        log.info(aList.stream().collect(Collectors.joining(StringPool.COMMA)));
        log.info(bList.stream().collect(Collectors.joining("\n")));
        log.info("#########################################################");
        log.info(roadAList.stream().collect(Collectors.joining("\n")));
        log.info(roadBList.stream().collect(Collectors.joining("\n")));

    }
}
