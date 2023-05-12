package com.utopian.tech.rbac.demo;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class RedisDelayQueueService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 添加任务到延迟队列中
    public void addTaskToDelayQueue(String taskId, long delayTime) {
        // 计算需要执行的时间戳
        long executeTime = System.currentTimeMillis() + delayTime;
        redisTemplate.opsForZSet().add("delay_queue", taskId, executeTime);
    }

    // 监听并处理延迟队列中的任务
    @Scheduled(fixedDelay = 500)
    public void listenDelayQueue() {
        // 获取需要执行的任务

        // 调用rangeByScore方法，返回指定范围内排序的元素集合，
        // 即元素的score在0到当前时间戳（System.currentTimeMillis()）之间的有序元素集合。
        // 0是Sorted Set的最小值，相当于不限制最小值；System.currentTimeMillis()为Sorted Set的最大值。

        // 接受rangeByScore方法的另外两个参数是offset和count，
        // 即分页查询的参数，这里指定offset为0，即从结果的第一个元素开始返回，count为100，即最多返回100个元素。
        Set<String> taskSet = redisTemplate.opsForZSet()
                .rangeByScore("delay_queue", 0, System.currentTimeMillis(), 0, 100);
        if (CollectionUtils.isEmpty(taskSet)) {
            return;
        }

        // 执行任务
        taskSet.forEach(taskId -> {
            // 将任务交给线程池去执行，这里省略具体实现

            // ...业务逻辑...
            log.info("{} 任务ID删除", taskId);

            // 执行完毕后，从延迟队列中删除该任务
            redisTemplate.opsForZSet().remove("delay_queue", taskId);
        });
    }
}