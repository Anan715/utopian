//package com.utopian.tech.demo.mq.demo.ex.fanout;
//
//import com.rabbitmq.client.BuiltinExchangeType;
//import com.rabbitmq.client.Channel;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//
//import java.util.UUID;
//
//public class FanoutExchangeProducer {
//    // 生产者向交换机发送一个消息，交换机发送给两个队列，队列分别绑定消费者，消费者都可以接收到消息
//    public static final String EXCHANGE_NAME = "FANOUT_EXCHANGE";
//
//    public static void main(String[] args) throws Exception{
//        Channel channel = RabbitMQUtils.getChannel();
//
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
//
//        String message = UUID.randomUUID().toString();
//
//        for (int i = 0; i < 1000; i++) {
//            message = message + i;
//            // MessageProperties.MINIMAL_PERSISTENT_BASIC 开启消息持久化
//            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
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
