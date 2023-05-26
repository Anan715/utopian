//package com.utopian.tech.demo.mq.demo.consumer;
//
//import com.rabbitmq.client.CancelCallback;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.DeliverCallback;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
//public class Consumer {
//    private static final String QUEUE_NAME = "FIRST_QUEUE";
//
//    public static void main(String[] args) throws IOException, TimeoutException {
//        // 获取信道
//        Channel channel = RabbitMQUtils.getChannel();
//
//        //声明 接收消息
//        DeliverCallback deliverCallback = (consumerTag, message) -> {
//            System.out.println("接收到消息 ： " + new String(message.getBody(), "UTF-8"));
//            //手动应答 参数：消息标记  + 是否批量应答信道中的消息
//            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
//        };
//
//        //取消消息时的回调
//        CancelCallback cancelCallback = consumerTag -> System.out.println("消恩消费被中断");
//
//        /**消费者消费消良
//         *1.消费哪个队列
//         *2.消费成功之后是否要自动应答 true 代表的自动应答false 代表手动应答
//         *3.消费者未成功消费的回调
//         *4消费者取英消费的回调
//         */
//        // 1 设置为非公平分发 || 0 为公平分发   ---> 能者多劳
//        // 当 值大于1时，该值的含义为预取值，即分配的消息数量已预定
//        channel.basicQos(1);
//        channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
//
//    }
//}
