package com.utopian.tech.demo.thread.syn;

public class Person {
    //输出10次value work 方法
    public synchronized void work(String value) {
        for (int i = 0; i < 10; i++) {
            System.out.println(value + i);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //输出10次value work 方法
    public static synchronized void synWork(String value) {
        for (int i = 0; i < 10; i++) {
            System.out.println(value + i);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
