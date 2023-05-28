package com.utopian.tech.demo.thread.syn;

public class SynchronizedTest3 {
    public static void main(String[] args) {
        //测试 synchronized 修饰普通方法
        Person person1 = new Person();
        Person person2 = new Person();  // 修改

        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("t1开始");
                Person.synWork("t1线程老板 叫person1 工作啦");
                System.out.println("t1结束");
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("t2开始");
                Person.synWork("t2线程老板 叫person2 工作啦"); // 修改
                System.out.println("t2结束");
            }
        };
        t1.start();
        t2.start();

    }

}
