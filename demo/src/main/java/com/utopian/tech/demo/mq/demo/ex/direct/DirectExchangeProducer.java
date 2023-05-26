//package com.utopian.tech.demo.mq.demo.ex.direct;
//
//import com.rabbitmq.client.BuiltinExchangeType;
//import com.rabbitmq.client.Channel;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//
//import java.util.UUID;
//
//public class DirectExchangeProducer {
//    // 生产者向交换机发送一个消息，交换机通过路由key分发给指定key的队列，指定队列绑定的的消费者接收到消息
//    public static final String EXCHANGE_NAME = "DIRECT_EXCHANGE";
//
//    public static void main(String[] args) throws Exception{
//        Channel channel = RabbitMQUtils.getChannel();
//
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
//
//        String message = UUID.randomUUID().toString();
//
//        for (int i = 0; i < 1000; i++) {
//            message = message + i;
//            // MessageProperties.MINIMAL_PERSISTENT_BASIC 开启消息持久化
//            channel.basicPublish(EXCHANGE_NAME, "black", null, message.getBytes("UTF-8"));
//            channel.basicPublish(EXCHANGE_NAME, "red", null, message.getBytes("UTF-8"));
//            channel.basicPublish(EXCHANGE_NAME, "white", null, message.getBytes("UTF-8"));
//            channel.basicPublish(EXCHANGE_NAME, "green", null, message.getBytes("UTF-8"));
//
//
//
//        }
//
//
//
//
//    }
//}
