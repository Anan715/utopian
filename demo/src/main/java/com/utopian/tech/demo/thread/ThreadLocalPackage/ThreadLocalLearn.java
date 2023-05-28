package com.utopian.tech.demo.thread.ThreadLocalPackage;

public class ThreadLocalLearn {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void setThreadName(String threadName) {
        threadLocal.set(threadName);
    }

    public String getThreadName() {
        return threadLocal.get();
    }

}
