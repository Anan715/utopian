package com.utopian.tech.demo.thread.singleton;

/**
 * 饿汉单例模式
 * 这个实现方式中，类的构造方法被私有化，只能通过静态方法getInstance()获取单例实例。
 * 静态变量instance在类加载时就被初始化一次，这也是饿汉式单例模式的特征之一。
 *
 * 在多线程环境下，由于静态变量会在类加载时进行初始化，因此这种方式是线程安全的。
 * 同时，由于只有一个实例存在，因此节省了创建实例所需的资源，但在某些情况下可能会导致资源浪费。
 */
public class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();  // 在类加载时即创建

    private HungrySingleton() {
        // 私有化构造方法，确保只有一个实例存在
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
