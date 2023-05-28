package com.utopian.tech.demo.thread;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Random;

/**
 * 生产者消费者线程模拟
 */
@Slf4j
public class ProducerConsumerTest {

    public static void main(String[] args) {
        MessageQuene messageQuene = new MessageQuene(10);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                Message message = new Message();
                message.setId(new Random(5).nextInt()).setData("啥也不是：");
                try {
                    log.info("数据存放：{}", message);
                    messageQuene.put(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "producer").start();
        }

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                Message take = messageQuene.take();
                log.info("获得数据: {}", take);
            }, "consumer").start();
        }


    }


    static class MessageQuene {


        private LinkedList<Message> messageLinkedList = new LinkedList<>();
        private Integer capacity;

        public MessageQuene(Integer capacity) {
            this.capacity = capacity;
        }

        public Message take() {
            synchronized (messageLinkedList) {
                while (messageLinkedList.isEmpty()) {
                    log.info("数据空了。。。。。。。");
                    try {
                        messageLinkedList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message message = messageLinkedList.removeFirst();
                // 在同步块内
                messageLinkedList.notifyAll();
                return message;
            }
        }

        public void put(Message message) throws InterruptedException {
            synchronized (messageLinkedList) {
                while (messageLinkedList.size() == capacity) {
                    log.info("数据满了。。。。。。。");
                    messageLinkedList.wait();
                }
                messageLinkedList.addLast(message);
                messageLinkedList.notifyAll();
            }
        }

    }


    @Data
    @Accessors(chain = true)
    static final class Message {
        private Integer id;

        private Object data;
    }


}
