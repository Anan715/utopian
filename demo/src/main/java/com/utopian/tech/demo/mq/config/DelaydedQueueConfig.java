//package com.utopian.tech.demo.mq.config;
//
//import com.utopian.tech.demo.mq.demo.BuiltinExchangeType;
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 由于mq默认的队列不支持消息排序，当需要实现死信队列时，mq只会关注第一个消息是否过期，
// * 造成后续过期时间短的消息延迟过期，所以，实现准确的过期队列需要使用rabbitMq插件
// * 自定义队列，集成插件开发队列
// */
//@Configuration
//public class DelaydedQueueConfig {
//
//    public static final String DELAYED_QUEUE_NAME = "DELAYED_QUEUE_NAME";
//    public static final String DELAYED_EXCHANGE_NAME = "DELAYED_EXCHANGE_NAME";
//    public static final String DELAYED_ROUTING_KEY = "DELAYED_ROUTING_KEY";
//
//
//
//
//    // 声明基于插件的交换机
//    @Bean("delayedExchange")
//    public CustomExchange delayedExchange(){
//        Map<String, Object> arguments = new HashMap<>();
//        arguments.put("x-delayed-type", BuiltinExchangeType.DIRECT);
//
//        /**
//         * 1.交换机的名称
//         * 2交换机的类型 (固定写法)
//         * 3.是否需要持久化
//         * 4.是否需要自动删除
//         * 5.其它的参数
//         */
//        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, arguments);
//    }
//
//
//    // 声明延迟队列
//    @Bean("delayedQueue")
//    public Queue delayedQueue(){
//        return new Queue(DELAYED_QUEUE_NAME);
//    }
//
//    // 绑定
//    @Bean
//    public Binding delayedQueueBindDelayedEx(@Qualifier("delayedQueue") Queue delayedQueue,
//                                   @Qualifier("delayedExchange") CustomExchange delayedExchange){
//        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
//    }
//
//
//
//
//}
