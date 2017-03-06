package concurrency.ch12;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by liuxiwen on 2017/3/6.
 */
public class CallableAndFuture02 {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();// 定义一个缓冲线程池
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool);// 将线程池扔进去
        for (int i = 1; i <= 5; i++) {
            final int seq = i;
            completionService.submit(// 用里面装的线程去执行这些任务，每个线程都会返回一个数据
                    () -> {
                        Thread.sleep(new Random().nextInt(5000));
                        return seq;
                    }
            );
        }
        for (int i = 0; i < 5; i++) {// 执行完了后再取出来
            try {
                System.out.print(completionService.take().get() + " ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
