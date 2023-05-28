package com.utopian.tech.demo.thread.singleton;

/**
 * 静态内部类实现单例模式，此时为懒加载模式
 * 对于静态内部类实现的单例模式，外部类的静态代码块是在类被加载时执行的，
 * 而内部类的静态代码块是在内部类第一次被加载时执行的，这个内部类指的是静态内部类。
 * 因此，静态内部类的静态代码块不是在外部类被加载时执行的，
 * 而是在第一次使用内部类时才会被加载和执行，从而实现了懒加载的效果。
 *
 * 需要注意的是，在使用静态内部类实现单例模式时，
 * 就算外部类已经被加载，内部类仍然不会被加载。
 * 只有在第一次使用 getInstance() 方法时才会初始化内部类并实例化单例。
 * 这样可以提高程序效率和节省资源，并保证线程安全。
 */
public class StaticInnerClassSingleTon {
        private StaticInnerClassSingleTon() {}

        public static StaticInnerClassSingleTon getInstance() {
            return SingletonHolder.instance;
        }

        private static class SingletonHolder {
            private static final StaticInnerClassSingleTon instance = new StaticInnerClassSingleTon();
        }

}
