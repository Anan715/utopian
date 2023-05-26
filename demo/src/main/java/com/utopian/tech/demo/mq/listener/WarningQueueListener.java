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
public class WarningQueueListener {

    @RabbitListener(queues = ConfirmConfig.BACKUP_WARNING_QUEUE)
    public void recMessage(Message message, Channel channel){
        String  mes = new String(message.getBody());
        log.info("{} 报警不可路由的消息：{}", new Date().toString(), mes);
    }




}
