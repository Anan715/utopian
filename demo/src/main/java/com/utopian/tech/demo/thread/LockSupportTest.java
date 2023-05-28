package com.utopian.tech.demo.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {


        // 看这个代码示例，这里我们在 t2 中先让线程 t1 unpark()，
        // 然后在 t1 中调用 park()， 结果并不会阻塞 t1 线程。
        // 因为在 t2 中调用 LockSupport.unpark(t1); 的时候相当于给 t1 提前准备好了许可证。

        //许可证不会累计

        //LockSupport.unpark(t1); 无论调用多少次，t1 的通行证只有一个，
        // 当在 t1 中调用两次 park() 方法时线程依然会被阻塞。


        Thread t1 = new Thread(() -> {
            System.out.println("A");
            LockSupport.park();
            System.out.println("被唤醒");
        });

        t1.start();

        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            System.out.println("B");
            LockSupport.unpark(t1);
        }).start();
    }

}
