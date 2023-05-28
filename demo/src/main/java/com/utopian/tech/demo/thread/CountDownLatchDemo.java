package com.utopian.tech.demo.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        int threadCount = 5; // 总共需要执行的线程数量

        CountDownLatch countDownLatch = new CountDownLatch(threadCount); // 创建CountDownLatch对象

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    // 线程执行任务
                    System.out.println(Thread.currentThread().getName() + "执行任务" + countDownLatch);
                    Thread.sleep(1000); // 模拟任务执行耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 线程任务完成后，调用countDown方法
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName() + "执行任务" + countDownLatch);
                }
            }).start();
        }

        countDownLatch.await(); // 等待所有线程执行完成，即countDownLatch的计数器为0

        // 所有线程执行完成后，执行下一步操作
        System.out.println("所有线程执行完成，执行下一步操作");
    }


}
