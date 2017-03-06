package concurrency.ch12;

import java.util.concurrent.*;

/**
 * Created by liuxiwen on 2017/3/6.
 */
public class CallableAndFuture01 {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 创建一个线程即可
        Future<String> future = threadPool.submit(() -> {
            Thread.sleep(2000);
            return "hello";
        });
        System.out.println("等待结果：");
        try {
            System.out.println("拿到结果：" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
