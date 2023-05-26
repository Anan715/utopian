//package com.utopian.tech.demo.mq.demo.pro;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.MessageProperties;
//import com.utopian.tech.demo.mq.demo.util.RabbitMQUtils;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
//public class Producer {
//
//    private static final String QUEUE_NAME = "FIRST_QUEUE";
//
//    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//
//        Channel channel = RabbitMQUtils.getChannel();
//
//        /**
//         * 生成一个队列
//         * 1.队列名称
//         * 2.队列是否持久化(磁盘) 默认情况消息存储在内存中
//         * 3.该队列是否只供一个消费者进行消费 是否进行消息共享，true可以多个消费者消费 false:只能一个消费者消费
//         * 4.是否自动删除最后一个消费者开还接以后 该队一句是否自动删除 true自动删除 false不自动删除
//         * 5.其它参数
//         */
//        // 声明队列
//        // 当队列已存在时，再次修改持久化属性，启动时会报异常，此时需要删除原队列，重新创建该队列(队列持久化，不是消息持久化)
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//        // 开启发布确认: 消息在MQ持久化后发送给生产者持久化的结果
//        // 发布确认对应三种操作：1 单个确认 2：批量确认 3：异步批量确认
//
//        // 单个确认
//        // 这是一种简单的确认方式，它是一种同步确认发布的方式，也就是发布一个消息之后只有它被确认发布，
//        // 后续的消息才能继续发布,waitForConfirmsOrDie(long)这个方法只有在消息被确认的时候才返回，
//        // 如果在指定时间范围内这个消息没有被确认那么它将抛出异常。
//        // 这种确认方式有一个最大的缺点就是:发布速度特别的慢，
//        // 因为如果没有确认发布的消息就会阻塞所有后续消息的发布，这种方式最多提供每秒不超过数百条发布消息的吞吐量。
//        // 当然对于某些应用程序来说这可能已经足够了。
//
//        // 批量确认
//        // 上面那种方式非常慢，与单个等待确认消息相比，先发布一批消息然后一起确认可以极大地提高吞吐量，
//        // 当然这种方式的缺点就是:当发生故障导致发布出现问题时，不知道是哪个消息出现问题了，
//        // 我们必须将整个批处理保存在内存中，以记录重要的信息而后重新发布消息。当然这种方案仍然是同步的，也一样阻塞消息的发布。
//
//        // 异步确认
//        // 异步确认虽然编程逻辑比上两个要复杂，但是性价比最高，无论是可靠性还是效率都没得说，
//        // 他是利用回调函数来达到消息可靠性传递的，这个中间件也是通过函数回调来保证是否投递成功。
//        channel.confirmSelect();
//
//        //发消息
//        String message = "hello world";
//        //初次使用
//        /**发达一个消费
//         *1发送到哪个交换机
//         *2.路由的Key值是哪个 本次是队列的名称
//         *3.其它参数信良
//         *4 发送消息的消息体
//         * */
//        // MessageProperties.MINIMAL_PERSISTENT_BASIC 开启消息持久化
//        channel.basicPublish("", QUEUE_NAME, MessageProperties.MINIMAL_PERSISTENT_BASIC, message.getBytes("UTF-8"));
//
//        // 等待确认回复
//        boolean confirms = channel.waitForConfirms();
//
//
//        System.out.println("消息发送完毕");
//    }
//}
