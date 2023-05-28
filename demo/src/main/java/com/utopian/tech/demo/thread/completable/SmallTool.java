package com.utopian.tech.demo.thread.completable;

import lombok.extern.slf4j.Slf4j;

import java.util.StringJoiner;

@Slf4j
public class SmallTool {
    public static void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printTimeAndThread(String tag) {
        String str = new StringJoiner("\t|\t")
                .add(String.valueOf(System.currentTimeMillis()))
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(Thread.currentThread().getName())
                .add(tag)
                .toString();
        log.info(str);

    }
}
