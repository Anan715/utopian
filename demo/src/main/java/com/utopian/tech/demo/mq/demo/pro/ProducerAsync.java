//package com.utopian.tech.demo.mq.demo.pro;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.ConfirmCallback;
//import com.rabbitmq.client.MessageProperties;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//
//import java.io.IOException;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentNavigableMap;
//import java.util.concurrent.ConcurrentSkipListMap;
//import java.util.concurrent.TimeoutException;
//
//public class ProducerAsync {
//
//    private static final String QUEUE_NAME = "FIRST_QUEUE";
//
//    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//
//        Channel channel = RabbitMQUtils.getChannel();
//
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//        // 异步确认
//        // 异步确认虽然编程逻辑比上两个要复杂，但是性价比最高，无论是可靠性还是效率都没得说，
//        // 他是利用回调函数来达到消息可靠性传递的，这个中间件也是通过函数回调来保证是否投递成功。
//
//        // 开启发布确认
//        channel.confirmSelect();
//
//        /**
//         * 记录消息发送，用于后续的失败消息处理
//         * 1：线程安全有序的一个哈希表 适用于高并发的况下经松的将序号与消息进行关联
//         * 2.轻松批量删除条目 只要给到序号
//         * 3.支持高并发《多线程)
//         */
//        ConcurrentSkipListMap<Long, Object> outstandingConfirms = new ConcurrentSkipListMap<>();
//
//        //发消息
//        String message = UUID.randomUUID().toString();
//
//
//        //消良确认成功 回调丽数
//        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
//            if(multiple){
//
//                // 批量确认
//                // 102:删除已经确认的信息，剩下的就是未成功的信息
//                ConcurrentNavigableMap<Long, Object> confirmed = outstandingConfirms.headMap(deliveryTag);
//                confirmed.clear();
//            } else {
//                // 非批量
//                outstandingConfirms.remove(deliveryTag);
//            }
//            System.out.println("确认的消息:" + deliveryTag);
//        };
//
//        /**
//         * 1. 消息的标记
//         * 2. 是否为批量确认
//         * */
//        //确认失败 回调函数
//        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
//            String mes= (String) outstandingConfirms.get(deliveryTag);
//            System.out.println("未确认的消息标记:" + deliveryTag + " || 未确认的消息:" + mes);
//        };
//
//        //准备消息的监听器 监听哪些消息成功了 哪些消息失败了
//        channel.addConfirmListener(ackCallback, nackCallback);
//
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            message = message + i;
//            // MessageProperties.MINIMAL_PERSISTENT_BASIC 开启消息持久化
//            channel.basicPublish("", QUEUE_NAME, MessageProperties.MINIMAL_PERSISTENT_BASIC, message.getBytes("UTF-8"));
//
//            // 101 记录发送过的消息
//            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
//        }
//        System.out.println("消息发送完毕");
//        System.out.println("消耗时间：" + (System.currentTimeMillis() - startTime) + " MS");
//    }
//}
