//package com.utopian.tech.demo.mq.demo.ex.deadqueue;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.DeliverCallback;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//
//public class DeadConsumer {
//
//    //死信交换机的名称
//    public static final String DEAD_EXCHANGE = "dead exchange";
//
//    public void recMes() throws Exception{
//        Channel channel = RabbitMQUtils.getChannel();
//
//        DeliverCallback deliverCallback = (consumerTag, message) -> {
//            System.out.println("死信队列接收到的消息： " + new String(message.getBody(), "UTF-8"));
//        };
//        channel.basicConsume(DEAD_EXCHANGE, true, deliverCallback, consumerTag -> {});
//    }
//}
