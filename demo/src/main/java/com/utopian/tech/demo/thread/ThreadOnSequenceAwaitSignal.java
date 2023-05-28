package com.utopian.tech.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ThreadOnSequenceAwaitSignal {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();
        new Thread(() -> {awaitSignal.print("a", a, b);}, "t1").start();
        new Thread(() -> {awaitSignal.print("b", b, c);}, "t1").start();
        new Thread(() -> {awaitSignal.print("c", c, a);}, "t1").start();

        Thread.sleep(500);
        awaitSignal.lock();
        try {
            log.info("主线程输出开始");
            a.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            awaitSignal.unlock();
        }
    }
}

@Slf4j
class AwaitSignal extends ReentrantLock{
    private Integer loopNum;
    public AwaitSignal(Integer loopNum){
        this.loopNum = loopNum;
    }

    public void print(String string, Condition condition, Condition nextCondition){

        for (Integer i = 0; i < loopNum; i++) {
            lock();
            try {
                // 某条件线程阻塞
                condition.await();
                log.info(string);
                // 唤醒下一条件线程
                nextCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }
}