package com.utopian.tech.demo.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * ConfirmCallback 交换机是否收到消息回调配置类，只适用于交换机
 * ReturnCallback 消息未发送成功时，消息回退接口
 */
@Slf4j
@Component  // 第一步
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {


    @Resource   // 第二步
    private RabbitTemplate rabbitTemplate;

    // 注入到 rabbitTemplate
    @PostConstruct   // 第三步
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }


    /**
     * 交换机确认回调方法
     * 1.发消良 交换机接收到了 回调
     * 1.1 correlationData 保存回调消息的ID及相关信感
     * 1.2交换机收到消息 ack = true
     * 1.3 cause null
     * <p>
     * 2.发消良 交换机接收失败了 回调
     * 2.1 correlationData 保存回调消息的ID及相关信息
     * 2.2交换机收到消感 ack = false
     * 2.3 cause 失败的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        String id = correlationData != null ? correlationData.getId() : "";
        if (ack) {
            log.info("交换机已接受到消息，id 为 ：{}", id);
        } else {
            log.info("交换机未接受到消息，id 为 ：{}，原因是： {}", id, cause);
        }
    }


    /**
     * 消息发送失败时回退 配置，只在失败时回退
     * 参数含义：
     *   消息
     *   失败错误码
     *   失败原因
     *   交换机
     *   路由key
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

        log.error("消息 {} 被交换机 {} 退回，原因是 {} ，路由 key为 {}",
                new String(message.getBody()),
                exchange,
                replyText,
                routingKey);
    }

}
