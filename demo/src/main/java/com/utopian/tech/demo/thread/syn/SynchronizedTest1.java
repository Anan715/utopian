package com.utopian.tech.demo.thread.syn;

public class SynchronizedTest1 {

    public static void main(String[] args) {
        // 当 synchronized 修饰 普通方法后，同一个对象被两个线程调用 结果
        // 测试 synchronized 修饰普通方法
        Person person1 = new Person();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("t1开始");
                person1.work("t1线程老板 叫person1 工作啦");
                System.out.println("t1结束");
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("t2开始");
                person1.work("t2线程老板 叫person1 工作啦");
                System.out.println("t2结束");
            }
        };
        t1.start();
        t2.start();

    }
}



