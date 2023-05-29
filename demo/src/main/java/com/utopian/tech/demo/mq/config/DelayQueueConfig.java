package com.utopian.tech.demo.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayQueueConfig {

    /**
 * 由于mq默认的队列不支持消息排序，当需要实现死信队列时，mq只会关注第一个消息是否过期，
 * 造成后续过期时间短的消息延迟过期，所以，实现准确的过期队列需要使用rabbitMq插件
 * 自定义队列，集成插件开发队列
 */

    public static final String DELAYED_QUEUE_NAME = "DELAYED_QUEUE_NAME";
    public static final String DELAYED_EXCHANGE_NAME = "DELAYED_EXCHANGE_NAME";
    public static final String DELAYED_ROUTING_KEY = "DELAYED_ROUTING_KEY";




    // 声明基于插件的交换机
    @Bean("delayedExchange")
    public CustomExchange delayedExchange(){



        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");

        /**
         * 1.交换机的名称
         * 2交换机的类型 (固定写法)
         * 3.是否需要持久化
         * 4.是否需要自动删除
         * 5.其它的参数
         */
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, arguments);
    }


    // 声明延迟队列
    @Bean("delayedQueue")
    public Queue delayedQueue(){
        return QueueBuilder.durable(DELAYED_QUEUE_NAME)
                .withArgument("x-message-ttl", 10000)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", "normal.queue")
                .build();
    }

    // 绑定
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(delayedQueue())
                .to(delayedExchange())
                .with(DELAYED_ROUTING_KEY)
                .noargs();
    }

}
