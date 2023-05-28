package com.utopian.tech.demo.thread;

import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {

    //上面的示例演示了如何使用 CompletableFuture 来执行一个异步任务。在这个例子中，我们创建了一个 CompletableFuture 对象，然后启动了一个新线程执行异步任务。异步任务完成后，我们通过调用 complete 方法来设置任务的结果。我们还可以通过调用 completeExceptionally 方法来设置任务的异常结果。
    //
    //在异步任务的结果可用时，我们可以通过调用 whenComplete 方法来处理结果。在这个例子中，我们打印出结果或异常信息。
    //
    //通过使用 CompletableFuture，我们可以避免使用回调函数或显式创建线程来执行异步任务。这使得异步编程更加简单、直观和可读。
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        // 异步任务
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                completableFuture.complete("Hello, CompletableFuture!");
            } catch (InterruptedException e) {
                completableFuture.completeExceptionally(e);
            }
        }).start();

        // 获取异步任务的结果
        completableFuture.whenComplete((result, throwable) -> {
            if (throwable != null) {
                System.out.println("Exception occurred: " + throwable.getMessage());
            } else {
                System.out.println(result);
            }
        });
    }



    public void method1(){
        StringJoiner stringJoiner = new StringJoiner("\t|\t").add(String.valueOf(System.currentTimeMillis()));
        String s = stringJoiner.toString();
    }
}
