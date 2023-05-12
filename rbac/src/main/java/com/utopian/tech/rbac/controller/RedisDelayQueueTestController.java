package com.utopian.tech.rbac.controller;

import com.utopian.tech.base.exception.UtopianException;
import com.utopian.tech.base.response.UtopianResponse;
import com.utopian.tech.rbac.demo.RedisDelayQueueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisDelayQueueTestController {

    @Resource
    private RedisDelayQueueService delayQueueService;

    @GetMapping("/delay/queue/add")
    public UtopianResponse<String> getToken(String taskId) throws UtopianException {
        delayQueueService.addTaskToDelayQueue(taskId, 10000L);
        return UtopianResponse.successWithoutData("任务添加成功");
    }
}
