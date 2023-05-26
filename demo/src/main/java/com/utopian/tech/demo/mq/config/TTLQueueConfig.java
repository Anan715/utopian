//package com.utopian.tech.demo.mq.config;
//
//
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class TTLQueueConfig {
//    // 普通交换机名称
//    public static final String NORMAL_CHANGE = "NORMAL_CHANGE";
//    // 死信交换机
//    public static final String DEAD_EXCHANGE = "DEAD_EXCHANGE";
//    // 队列
//    public static final String NORMAL_A_QUEUE = "NORMAL_A_QUEUE";
//    public static final String NORMAL_B_QUEUE = "NORMAL_B_QUEUE";
//    public static final String NORMAL_C_QUEUE = "NORMAL_C_QUEUE";
//    public static final String DEAD_QUEUE = "DEAD_QUEUE";
//
//    // 死信队列路由key
//    public static final String DEAD_ROUTING_KEY = "DEAD_ROUTING_KEY";
//    // 普通队列A路由key
//    public static final String A_ROUTING_KEY = "A_ROUTING_KEY";
//    // 普通队列B路由key
//    public static final String B_ROUTING_KEY = "B_ROUTING_KEY";
//    public static final String C_ROUTING_KEY = "C_ROUTING_KEY";
//
//    // 声明普通交换机
//    @Bean("normalExchange")
//    public DirectExchange normalExchange() {
//        return new DirectExchange(NORMAL_CHANGE);
//    }
//
//
//    // 声明死信交换机
//    @Bean("deadExchange")
//    public DirectExchange deadExchange() {
//        return new DirectExchange(DEAD_EXCHANGE);
//    }
//
//
//    // 声明普通队列,消息过期时间为 10S
//    @Bean("normalQueueA")
//    public Queue queueA() {
//        Map<String, Object> arguments = new HashMap<>(3);
//        // 设置交换机
//        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
//        // 设置死信队列的路由 KEY
//        arguments.put("x-dead-letter-routing-key", DEAD_ROUTING_KEY);
//        // 设置过期时间
//        arguments.put("x-message-ttl", 10000);
//        return QueueBuilder.durable(NORMAL_A_QUEUE).withArguments(arguments).build();
//    }
//
//
//
//    // 声明普通队列,消息过期时间为 60S
//    @Bean("normalQueueB")
//    public Queue queueB() {
//        Map<String, Object> arguments = new HashMap<>(3);
//        // 设置交换机
//        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
//        // 设置死信队列的路由 KEY
//        arguments.put("x-dead-letter-routing-key", DEAD_ROUTING_KEY);
//        // 设置过期时间
//        arguments.put("x-message-ttl", 60000);
//        return QueueBuilder.durable(NORMAL_B_QUEUE).withArguments(arguments).build();
//    }
//
//
//    // 声明死信队列
//    @Bean("deadQueue")
//    public Queue queueD() {
//        return QueueBuilder.durable(DEAD_QUEUE).build();
//    }
//
//    // 路由key,绑定交换机与队列
//    @Bean
//    public Binding aQueueBindNorEx(@Qualifier("normalQueueA") Queue normalQueueA,
//                                   @Qualifier("normalExchange") DirectExchange normalExchange){
//        return BindingBuilder.bind(normalQueueA).to(normalExchange).with(A_ROUTING_KEY);
//    }
//
//    @Bean
//    public Binding bQueueBindNorEx(@Qualifier("normalQueueB") Queue normalQueueB,
//                                   @Qualifier("normalExchange") DirectExchange normalExchange){
//        return BindingBuilder.bind(normalQueueB).to(normalExchange).with(B_ROUTING_KEY);
//    }
//
//    @Bean
//    public Binding deadQueueBindNorEx(@Qualifier("deadQueue") Queue deadQueue,
//                                   @Qualifier("deadExchange") DirectExchange deadExchange){
//        return BindingBuilder.bind(deadQueue).to(deadExchange).with(B_ROUTING_KEY);
//    }
//
//
//    // 声明普通队列,无消息过期时间，消息过期时间由生产者指定
//    @Bean("normalQueueC")
//    public Queue queueC() {
//        Map<String, Object> arguments = new HashMap<>(3);
//        // 设置交换机
//        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
//        // 设置死信队列的路由 KEY
//        arguments.put("x-dead-letter-routing-key", DEAD_ROUTING_KEY);
//        return QueueBuilder.durable(NORMAL_C_QUEUE).withArguments(arguments).build();
//    }
//
//    @Bean
//    public Binding cQueueBindNorEx(@Qualifier("normalQueueC") Queue normalQueueC,
//                                   @Qualifier("normalExchange") DirectExchange normalExchange){
//        return BindingBuilder.bind(normalQueueC).to(normalExchange).with(C_ROUTING_KEY);
//    }
//
//}
