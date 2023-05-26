//package com.utopian.tech.demo.mq.demo.ex.fanout;
//
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.DeliverCallback;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//
//public class FanoutExchangeConsumer {
//
//    public static final String EXCHANGE_NAME = "FIRST_EXCHANGE";
//
//    public static void main(String[] args) throws Exception{
//        Channel channel = RabbitMQUtils.getChannel();
//
//        // 声明一个交换机 参数：交换机名称，交换机类型 fanout：扇出
//        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
//
//        // 声明一个队列
//        String queueName = channel.queueDeclare().getQueue();
//
//        // 绑定交换机与队列 参数：队列名称，交换机名称，路由key
//        channel.queueBind(queueName, EXCHANGE_NAME, "");
//
//        DeliverCallback deliverCallback = (consumerTag, message) -> {
//            System.out.println("接收到的消息： " + new String(message.getBody(), "UTF-8"));
//        };
//        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
//    }
//}
