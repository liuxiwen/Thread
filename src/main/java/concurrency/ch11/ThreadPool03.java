package concurrency.ch11;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuxiwen on 2017/3/6.
 */
public class ThreadPool03 {

    public static void main(String[] args) {

        // 拿到定时器线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);
        for (int i = 1; i <= 5; i++) {// 执行五次任务
            threadPool.schedule(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "bombing");
            }, 2, TimeUnit.SECONDS);
        }
    }
}
