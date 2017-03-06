package concurrency.ch11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuxiwen on 2017/3/6.
 */
public class ThreadPool01 {

    // 线程池的概念与Executors类的使用
    public static void main(String[] args) {

        // 固定线程池：创建固定线程数去执行线程的任务，这里创建三个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {// 向池子里扔10个任务
            final int task = i;
            threadPool.execute(() -> {// execute()方法表示向池子中扔任务，任务即一个Runnable
                for (int j = 1; j <= 5; j++) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " looping of " + j + " for task of " + task);
                }
            });
        }
        System.out.println("all of 10 tasks have committed.");
        threadPool.shutdown();// 执行完任务后关闭
    }
}
