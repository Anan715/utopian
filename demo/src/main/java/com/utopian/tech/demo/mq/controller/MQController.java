package com.utopian.tech.demo.mq.controller;


import com.utopian.tech.base.response.UtopianResponse;
import com.utopian.tech.demo.mq.config.ConfirmConfig;
import com.utopian.tech.demo.mq.config.DelayQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
public class MQController {


    // 普通交换机名称
    public static final String NORMAL_CHANGE = "NORMAL_CHANGE";
    // 死信交换机
    public static final String DEAD_EXCHANGE = "DEAD_EXCHANGE";
    // 队列
    public static final String NORMAL_A_QUEUE = "NORMAL_A_QUEUE";
    public static final String NORMAL_B_QUEUE = "NORMAL_B_QUEUE";
    public static final String DEAD_QUEUE = "DEAD_QUEUE";

    // 死信队列路由key
    public static final String DEAD_ROUTING_KEY = "DEAD_ROUTING_KEY";
    // 普通队列A路由key
    public static final String A_ROUTING_KEY = "A_ROUTING_KEY";
    // 普通队列B路由key
    public static final String B_ROUTING_KEY = "B_ROUTING_KEY";


    public static final String NORMAL_C_QUEUE = "NORMAL_C_QUEUE";
    public static final String C_ROUTING_KEY = "C_ROUTING_KEY";


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("/send/message")
    public void sendMessage() {

        String message = UUID.randomUUID().toString();
        log.info("当前时间：{} 发送一条信息 -{}给两个普通队列 ", new Date().toString(), message);
        rabbitTemplate.convertAndSend(NORMAL_CHANGE, A_ROUTING_KEY, "发送10秒超时的消息：" + message);
        rabbitTemplate.convertAndSend(NORMAL_CHANGE, B_ROUTING_KEY, "发送60秒超时的消息：" + message);
    }

    @GetMapping("/send/message/time")
    public void sendMessageWithTime() {

        String message = UUID.randomUUID().toString();
        log.info("当前时间：{} 发送一条信息 -{}给两个普通队列 ", new Date().toString(), message);
        rabbitTemplate.convertAndSend(NORMAL_CHANGE, C_ROUTING_KEY, "发送10秒超时的消息：" + message, msg -> {
            msg.getMessageProperties().setExpiration("5000");
            return msg;
        });

    }


    @GetMapping("/send/message/delay")
    public void sendMessageWithPlugin() {

        String message = UUID.randomUUID().toString();
        Integer delayTime = 4321;
        log.info("当前时间：{} 发送一条信息 -{} 延迟 {} 给队列 {} ", new Date().toString(), message, delayTime, DelayQueueConfig.DELAYED_QUEUE_NAME);
        rabbitTemplate.convertAndSend(DelayQueueConfig.DELAYED_EXCHANGE_NAME,
                DelayQueueConfig.DELAYED_ROUTING_KEY,
                "发送10秒超时的消息：" + message,
                msg -> {
                    msg.getMessageProperties().setDelay(delayTime);
                    return msg;
                });
    }


    @GetMapping("/send/message/confirm")
    public UtopianResponse sendMessageConfirm() {

        // 确认回调函数参数
        CorrelationData correlationData = new CorrelationData(String.valueOf(System.currentTimeMillis()));
        String message = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE,
                ConfirmConfig.CONFIRM_ROUTE_KEY + "123", message, correlationData);
        log.info("发送消息为：{}", message);
        return UtopianResponse.successWithData(message);
    }

}
