//package com.utopian.tech.demo.thread;
//
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.awt.print.Book;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 某线程等待另一线程的结果
// */
//@Slf4j
//public class GuardedObjectTest {
//    public static void main(String[] args) {
//        GuardedObject guard = new GuardedObject();
//        new Thread(() -> {
//            log.info("等待结果");
//            Object response = guard.getResponse();
//            log.info("其他线程返回结果：{}", response);
//        }, "t1").start();
//
//        new Thread(() -> {
//
//            try {
//                Thread.sleep(1000);
//                List<Book> bookList = new ArrayList<>();
//                for (int i = 0; i < 100; i++) {
//                    Book book = new Book();
//                    book.setName("JAVA" + i);
//                    book.setNumber(i);
//                    bookList.add(book);
//                }
//                guard.completeResponse(bookList);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, "t2").start();
//    }
//}
//
//
//class GuardedObject{
//    private Object response;
//
//    public Object getResponse() {
//        synchronized (this) {
//            while (response == null){
//                try {
//                    this.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            return response;
//        }
//    }
//
//    public void completeResponse(Object obj) {
//        synchronized (this) {
//            // 赋值
//            this.response = obj;
//            this.notifyAll();
//        }
//    }
//}