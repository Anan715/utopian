package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptTest1 {
    public static void main(String[] args) throws InterruptedException {
        log.info("开始");
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
                log.info("睡眠后的逻辑");
            } catch (InterruptedException e) {
                e.printStackTrace();

                // 如果任何线程打断当前线程，当前线程的打断标记在InterruptedException抛出时会被清除掉。
                // 所以说，我们在捕捉到InterruptedException后想要再拿到线程t1的打断标记基本上是不可能的
                log.info("线程被打断： {}", Thread.currentThread().isInterrupted());
            }
        });

        t.start();
        Thread.sleep(200);
        t.interrupt();
        t.join();

        log.info("###############################################");

        Thread t1 = new Thread(() -> {
            int i = 0;
            // 循环自增
            while (true) {
                System.out.println(i);
                i++;
                // 判断是否有打断标记
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被打断，跳出循环");
                    // 如果有打断标记，就跳出循环
                    break;
                }
            }
        });

        t1.start();
        Thread.sleep(2);
        t1.interrupt();
        t1.join();
    }
}
