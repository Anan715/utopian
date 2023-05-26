//package com.utopian.tech.demo.mq.demo.ex.deadqueue;
//
//import com.rabbitmq.client.BuiltinExchangeType;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.DeliverCallback;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class NormalConsumer {
//
//    // 普通交换机的名称
//    public static final String NORMAL_EXCHANGE = "normal_exchange";
//    //普通队列的名称
//    public static final String NORMAL_QUEUE = "normal_queue";
//
//    //死信交换机的名称
//    public static final String DEAD_EXCHANGE = "dead exchange";
//    //死信队列的名称
//    public static final String DEAD_QUEUE ="dead_queue";
//    //正常队列的路由 key
//    public static final String NORMAL_ROUTING_KEY ="normal_routing_key";
//    //死信队列的路由 key
//    public static final String DEAD_ROUTING_KEY ="dead_routing_key";
//
//
//    public void recMes() throws Exception{
//        Channel channel = RabbitMQUtils.getChannel();
//
//        // 声明死信交换机和普通交换机
//        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
//        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
//
//        // 声明死信队列和普通队列
//        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);
//
//        // 声明扑通队列，在消息变为死信时，转发给死信队列
//        Map<String, Object> arguments = new HashMap<>();
//        // 设置消息的过期时间,也可以在生产者生产消息时指定过期时间
//        // arguments.put("x-message-ttl", 10000);
//
//        // 设置交换机
//        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
//        // 设置死信队列的路由 KEY
//        arguments.put("x-dead-letter-routing-key", DEAD_ROUTING_KEY);
//        // 设置队列的最大长度
//        arguments.put("x-max-length", 10);
//
//        channel.queueDeclare(NORMAL_QUEUE, false, false, false, arguments);
//
//        // 绑定交换机与队列
//        channel.queueBind(NORMAL_EXCHANGE, NORMAL_QUEUE, NORMAL_ROUTING_KEY);
//        channel.queueBind(DEAD_EXCHANGE, DEAD_QUEUE, DEAD_ROUTING_KEY);
//
//
//        DeliverCallback deliverCallback = (consumerTag, message) -> {
//            String mes = new String(message.getBody(), "UTF-8");
//            if(mes.contains("abc")){
//                System.out.println("此消息被拒绝：" + mes);
//                // 信道拒绝消息的标签， 是否放回正常队列，不放回队列就是死信队列
//                channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
//            } else {
//                System.out.println("正常队列接收到的消息： " + mes);
//                // 接收信息的标记， 是否批量应答，消息拒绝一定要在手动应答的情况下
//                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
//            }
//        };
//        channel.basicConsume(NORMAL_QUEUE, false, deliverCallback, consumerTag -> {});
//    }
//}
