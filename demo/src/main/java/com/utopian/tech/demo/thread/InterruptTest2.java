package com.utopian.tech.demo.thread;


import com.utopian.tech.demo.thread.completable.SmallTool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptTest2 {
    public static void main(String[] args) throws InterruptedException {

        Thread car = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            int i = 1;
            while (System.currentTimeMillis() - startTime < 28) {
                i += 1;

                // if(Thread.currentThread().isInterrupted()){  isInterrupted 只是查看是否被打断过，被打断状态会一直被记录，不会清除
//                if (Thread.interrupted()) {    // interrupted 不仅会查看是否被打断过，还会清除打断记录
                if (Thread.currentThread().isInterrupted()) {    // interrupted 不仅会查看是否被打断过，还会清除打断记录
                    SmallTool.printTimeAndThread(String.valueOf(Thread.currentThread().isInterrupted()));
                    SmallTool.printTimeAndThread("向左行驶一米");
                    if (i > 5) break;
                } else {
                    SmallTool.printTimeAndThread("向前行驶一米");
                    if (i > 5) break;
                }
            }
        });

        car.start();
        Thread.sleep(2);
        car.interrupt();
        Thread.sleep(5);
        car.join();
    }
}
