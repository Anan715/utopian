//package com.utopian.tech.demo.mq.demo.ex.direct;
//
//
//import com.rabbitmq.client.BuiltinExchangeType;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.DeliverCallback;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//
//public class DirectExchangeConsumer {
//
//    public static final String EXCHANGE_NAME = "DIRECT_EXCHANGE";
//    public static final String QUEUE_NAME = "DIRECT_QUEUE";
//
//    public static void main(String[] args) throws Exception{
//        Channel channel = RabbitMQUtils.getChannel();
//
//        // 声明一个交换机 参数：交换机名称，交换机类型 fanout：扇出
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
//
//        // 声明一个队列
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//        // 绑定交换机与队列 参数：队列名称，交换机名称，路由key, 可以接受路由的key
//        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "black");
//        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "red");
//        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "green");
//
//        DeliverCallback deliverCallback = (consumerTag, message) -> {
//            System.out.println("接收到的消息： " + new String(message.getBody(), "UTF-8"));
//        };
//        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
//    }
//}
