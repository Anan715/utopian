package com.utopian.tech.demo.thread;


import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SingleThreadExcutorTest {


    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            int j = i;
            pool.execute(() -> {
                log.info("计算结果：{}", new BigDecimal(1/(j-2)));
            });
        }
    }
}
