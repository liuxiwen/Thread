package concurrency.ch5;

/**
 * Created by liuxiwen on 2017/2/28.
 */
public class TraditionalThreadCommunication01 {

    public static void main(String[] args) {

        // 开启一个子线程
        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                synchronized (TraditionalThreadCommunication01.class) {
                    // 子线程任务：执行10次
                    for (int j = 1; j <= 10; j++) {
                        System.out.println("sub thread sequence of " + j + ", loop of " + i);
                    }
                }
            }
        }).start();

        // main方法即主线程
        for (int i = 1; i <= 50; i++) {
            synchronized (TraditionalThreadCommunication01.class) {
                // 主线程任务：执行5次
                for (int j = 1; j <= 5; j++) {
                    System.out.println("main thread sequence of " + j + ", loop of " + i);
                }
            }
        }
    }
}
