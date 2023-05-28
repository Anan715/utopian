package com.utopian.tech.demo.thread;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        // 创建一个CyclicBarrier对象，参数2表示需要等待的线程数
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        // 创建2个线程并启动
        Thread t1 = new Thread(new Task(cyclicBarrier), "Thread 1");
        Thread t2 = new Thread(new Task(cyclicBarrier), "Thread 2");
        t1.start();
        t2.start();

        // 主线程等待2个线程执行完成
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Task implements Runnable {
        private final CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started running");
            try {
                Thread.sleep(1000); // 模拟线程执行时间
                cyclicBarrier.await(); // 等待其他线程
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }
}
