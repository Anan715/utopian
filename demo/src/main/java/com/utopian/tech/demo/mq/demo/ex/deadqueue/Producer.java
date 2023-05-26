//package com.utopian.tech.demo.mq.demo.ex.deadqueue;
//
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.Channel;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//
//import java.util.UUID;
//
///**
// * 死信队列生产者
// */
//public class Producer {
//
//    // 普通交换机的名称
//    public static final String NORMAL_EXCHANGE = "normal_exchange";
//    //普通队列的名称
//    public static final String NORMAL_QUEUE = "normal_queue";
//    //正常队列的路由 key
//    public static final String NORMAL_ROUTING_KEY = "normal_routing_key";
//
//    public void sendMes() throws Exception {
//        Channel channel = RabbitMQUtils.getChannel();
//
//        // 交换机只可以声明一次
//        // channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
//
//
//        // 设置消息属性 过期时间 单位毫秒
//        AMQP.BasicProperties props = new AMQP.BasicProperties().builder().expiration("10000").build();
//
//
//        for (int i = 0; i < 1000; i++) {
//            String message = UUID.randomUUID().toString();
//            // MessageProperties.MINIMAL_PERSISTENT_BASIC 开启消息持久化
//            channel.basicPublish(NORMAL_EXCHANGE, NORMAL_ROUTING_KEY, null, message.getBytes("UTF-8"));
//
//        }
//    }
//
//
//}
