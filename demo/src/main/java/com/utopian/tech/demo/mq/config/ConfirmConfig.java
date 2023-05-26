package com.utopian.tech.demo.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfirmConfig {
    public static final String CONFIRM_EXCHANGE = "CONFIRM_EXCHANGE";
    public static final String CONFIRM_QUEUE = "CONFIRM_QUEUE";
    public static final String CONFIRM_ROUTE_KEY = "CONFIRM_ROUTE_KEY";

    // 声明确认交换机
    @Bean("confirmExchange")
    public DirectExchange directExchange() {

        return (DirectExchange) ExchangeBuilder.directExchange(CONFIRM_EXCHANGE)
                .durable(true)
                .withArgument("alternate-exchange", BACKUP_CONFIRM_EXCHANGE)
                .build();
    }


    // 声明队列
    @Bean("confirmQueue")
    public Queue delayedQueue() {
        // 声明优先级队列
        // Map<String, Object> arguments = new HashMap<>();
        // arguments.put("x-max-priority", 10); // 优先级 0-255之间，尽量不使用太大值，避免排序浪费性能
        // return new Queue(CONFIRM_QUEUE, false ,false, false, arguments);
        return new Queue(CONFIRM_QUEUE);
    }

    // 绑定
    @Bean
    public Binding queueBindExchange(@Qualifier("confirmQueue") Queue confirmQueue,
                                     @Qualifier("confirmExchange") DirectExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTE_KEY);
    }

    // ##########################################################################################

    /**
     * 改造为备份交换机
     */

    // 备份交换机
    public static final String BACKUP_CONFIRM_EXCHANGE = "BACKUP_CONFIRM_EXCHANGE";
    // 备份队列
    public static final String BACKUP_CONFIRM_QUEUE = "BACKUP_CONFIRM_QUEUE";
    // 报警队列
    public static final String BACKUP_WARNING_QUEUE = "BACKUP_WARNING_QUEUE";

    // 声明备份交换机
    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange(BACKUP_CONFIRM_EXCHANGE);
    }

    // 声明备份队列
    @Bean("backupQueue")
    public Queue backupQueue() {
        return new Queue(BACKUP_CONFIRM_QUEUE);
    }

    // 声明报警队列
    @Bean("backupWarningQueue")
    public Queue backupWarningQueue() {
        return new Queue(BACKUP_WARNING_QUEUE);
    }

    // 绑定备份交换机与备份队列和报警队列
    // 绑定
    @Bean
    public Binding backupQueueBindExchange(@Qualifier("backupWarningQueue") Queue backupQueue,
                                     @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

    @Bean
    public Binding warningQueueBindExchange(@Qualifier("backupWarningQueue") Queue backupWarningQueue,
                                     @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(backupWarningQueue).to(backupExchange);
    }






}
