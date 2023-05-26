package com.utopian.tech.demo.mq.listener;

import com.rabbitmq.client.Channel;
import com.utopian.tech.demo.mq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class ConfirmQueueListener {

    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE)
    public void recMessage(Message message, Channel channel){
        String  mes = new String(message.getBody());
        log.info("{} 收到 confirmQueue消息：{}", new Date().toString(), mes);
    }




}
