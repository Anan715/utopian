//package com.utopian.tech.demo.mq.listener;
//
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Slf4j
//@Component
//public class MQMessageListener {
//
//    // 普通交换机名称
//    public static final String NORMAL_CHANGE = "NORMAL_CHANGE";
//    // 死信交换机
//    public static final String DEAD_EXCHANGE = "DEAD_EXCHANGE";
//    // 队列
//    public static final String NORMAL_A_QUEUE = "NORMAL_A_QUEUE";
//    public static final String NORMAL_B_QUEUE = "NORMAL_B_QUEUE";
//    public static final String DEAD_QUEUE = "DEAD_QUEUE";
//
//    // 死信队列路由key
//    public static final String DEAD_ROUTING_KEY = "DEAD_ROUTING_KEY";
//    // 普通队列A路由key
//    public static final String A_ROUTING_KEY = "A_ROUTING_KEY";
//    // 普通队列B路由key
//    public static final String B_ROUTING_KEY = "B_ROUTING_KEY";
//
//
//
//    @RabbitListener(queues = DEAD_QUEUE)
//    public void recMessage(Message message, Channel channel){
//        String  mes = new String(message.getBody());
//        log.info("{} 收到死信队列消息：{}", new Date().toString(), mes);
//    }
//
//
//
//
//}
