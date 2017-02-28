package concurrency.ch5;

/**
 * Created by liuxiwen on 2017/2/28.
 */
public class TraditionalThreadCommunication02 {

    public static void main(String[] args) {

        Business business = new Business();// new一个线程处理类
        // 开启一个子线程
        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub(i);
            }
        }).start();

        // main方法即主线程
        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }
    }
}

// 要用到的共同数据（包括同步锁）或共同的若干个方法应该归在同一个类上，这种设计正好体现了高类聚和程序的健壮性
class Business {

    public synchronized void sub(int i) {

        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread sequence of " + j + ", loop of " + i);
        }
    }

    public synchronized void main(int i) {

        for (int j = 1; j <= 5; j++) {
            System.out.println("main thread sequence of " + j + ", loop of " + i);
        }
    }
}
