package com.utopian.tech.demo.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 各个线程实现顺序输出 abcabcabcabcabc 实现方式
 */
@Slf4j
public class ThreadOnSequenceTest3 {


    public static void main(String[] args) {

        WaitNotify wn = new WaitNotify(1, 5);
        for (int i = 0; i < 5; i++) {

            new Thread(() -> {

                wn.print("a", 1, 2);
            }, "t1").start();


            new Thread(() -> {
                wn.print("b", 2, 3);
            }, "t2").start();


            new Thread(() -> {
                wn.print("c", 3, 1);
            }, "t3").start();


        }

    }

}

@Data
@Slf4j
class WaitNotify {
    private int flag;
    private int loopNum;

    public WaitNotify(int flag, int loopNum) {
        this.flag = flag;
        this.loopNum = loopNum;
    }


    public void print(String str, int waitFlag, int nextFlag) {

        synchronized (this) {
            while (flag != waitFlag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info(str);
            flag = nextFlag;
            this.notifyAll();
        }
    }


}