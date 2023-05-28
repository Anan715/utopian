package com.utopian.tech.demo.thread.singleton;


/**
 * DCL（双重检索机制）实现单例模式 (饱汉单例模式)
 * 在该实现方式中，volatile 修饰的 instance 可以保证多线程环境下对其的可见性，
 * 而双重检查锁定语句 if (instance == null) 可以避免重复创建对象的问题。具体地，
 * 当多个线程同时调用 getInstance() 方法时，
 * 第一个线程请求进入同步块时，会对 Singleton 类进行加锁，然后再次检查 instance 的值是否为空，
 * 如果为空，则进行实例化。之后，第二个线程进入同步块时，发现 instance 已经不为空，直接返回该对象即可。
 *
 * 相较于其他实现方式而言，DCL 实现单例模式的优势在于它既能保证线程安全性，
 * 又能够完成延迟加载，相比于直接在方法上加锁的实现而言，性能方面也有很大的提升。
 * 不过需要注意的是，对于使用 DCL 实现的单例模式，在某些 Java 版本或者 JVM 中，由于指令重排序等原因，
 * 可能会出现线程非安全等问题，因此可能需要进行一些额外的处理，比如使用 volatile 等关键字。
 */
public class DCLSingleton {
    private volatile static DCLSingleton instance;
    private DCLSingleton() {}
    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized(DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }

}
