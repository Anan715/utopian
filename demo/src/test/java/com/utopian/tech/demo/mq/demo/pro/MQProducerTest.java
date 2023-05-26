//package com.utopian.tech.demo.mq.demo.pro;
//
//import com.rabbitmq.client.*;
//import com.utopian.tech.demo.MiddleWareApplication;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//import junit.framework.TestCase;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.TimeoutException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MiddleWareApplication.class)
//@EnableAutoConfiguration
//public class MQProducerTest extends TestCase {
//
//    private static final String QUEUE_NAME = "FIRST_MES";
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
//    @Test
//    public void sendMes() throws IOException, TimeoutException {
//        //创建一个连接工厂
//        ConnectionFactory factory = new ConnectionFactory();
//        //工厂IP 连按RabbitMQ的队列
//        factory.setHost("127.0.0.1");
//        //用户名
//        factory.setUsername("guest");
//        //签码
//        factory.setPassword("guest");
//        //创建连接
//        Connection connection = factory.newConnection();
//        //获取信道
//        Channel channel = connection.createChannel();
//
//        /**
//         * 生成一个队列
//         * 1.队列名称
//         * 2.从列里面的消息是否持久化(磁盘) 默认情况消息存储在内存中
//         * 3.该队列是否只供一个消费者进行消费 是否进行消息共享，true可以多个消费者消费 false:只能一个消费者消费
//         * 4.是否自动删除最后一个消费者开还接以后 该队一句是否自动删除 true自动删除 false不自动删除
//         * 5.其它参数
//         */
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        //发消息
//        //初次使用
//        /**发达一个消费
//         *1发送到哪个交换机
//         *2.路由的Key值是哪个 本次是队列的名称
//         *3.其它参数信良
//         *4 发送消息的消息体
//         * */
//        for (int i = 0; i < 5; i++) {
//            String message = "hello world : " + i;
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        }
//        System.out.println("消息发送完毕");
//    }
//
//
//    @Test
//    public void recMes() throws IOException, TimeoutException {
//
//        //创建一个连接工厂
//        ConnectionFactory factory = new ConnectionFactory();
//        //工厂IP 连按RabbitMQ的队列
//        factory.setHost("127.0.0.1");
//        //用户名
//        factory.setUsername("guest");
//        //签码
//        factory.setPassword("guest");
//        //创建连接
//        Connection connection = factory.newConnection();
//        //获取信道
//        Channel channel = connection.createChannel();
//        //声明 接收消息
//        DeliverCallback deliverCallback = (consumerTag, message) -> System.out.println("收到消息：" + new String(message.getBody()));
//        //取消消息时的回调
//        CancelCallback cancelCallback = consumerTag -> System.out.println("消恩消费被中断");
//
//        /**消费者消费消良
//         *1.消费哪个队列
//         *2.消费成功之后是否要自动应答 true 代表的自动应答false 代表手动应答
//         *3.消费者未成功消费的回调
//         *4消费者取英消费的回调
//         */
//        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
//
//    }
//
//
//    public void sendBatch() throws Exception {
//
//        Channel channel = RabbitMQUtils.getChannel();
//
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//        channel.confirmSelect();
//
//        String message = UUID.randomUUID().toString();
//
//        long startTime = System.currentTimeMillis();
//
//        ConfirmCallback ackCallback = (deliveryTag, multiple) -> System.out.println("确认的消息:" + deliveryTag);
//
//        ConfirmCallback nackCallback = (deliveryTag, multiple) -> System.out.println("未确认的消息:" + deliveryTag);
//
//        channel.addConfirmListener(ackCallback, nackCallback);
//        for (int i = 0; i < 1000; i++) {
//            message = message + i;
//            channel.basicPublish("", QUEUE_NAME, MessageProperties.MINIMAL_PERSISTENT_BASIC, message.getBytes("UTF-8"));
//
//            boolean confirms = channel.waitForConfirms();
//        }
//        System.out.println("消息发送完毕");
//        System.out.println("消耗时间：" + (System.currentTimeMillis() - startTime) + " MS");
//    }
//
//    @Test
//    public void deadRecMes() throws Exception {
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
//            System.out.println("正常队列接收到的消息： " + new String(message.getBody(), "UTF-8"));
//        };
//        channel.basicConsume(NORMAL_QUEUE, true, deliverCallback, consumerTag -> {
//        });
//    }
//
//
//}