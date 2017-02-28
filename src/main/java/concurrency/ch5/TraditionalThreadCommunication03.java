package concurrency.ch5;

/**
 * Created by liuxiwen on 2017/2/28.
 */
public class TraditionalThreadCommunication03 {

    public static void main(String[] args) {

        Business2 business2 = new Business2();// new一个线程处理类
        // 开启一个子线程
        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business2.sub(i);
            }
        }).start();

        // main方法即主线程
        for (int i = 1; i <= 50; i++) {
            business2.main(i);
        }
    }
}

// 要用到的共同数据（包括同步锁）或共同的若干个方法应该归在同一个类上，这种设计正好体现了高类聚和程序的健壮性
class Business2 {

    private boolean bShouldSub = true;

    public synchronized void sub(int i) {

        while (!bShouldSub) {// 如果不轮到自己执行，就睡
            try {
                this.wait();// 调用wait()方法的对象必须和synchronized锁对象一致，这里synchronized在方法上，所以用this
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread sequence of " + j + ", loop of " + i);
        }

        bShouldSub = false;// 改变标记
        this.notify();// 唤醒正在等待的主线程
    }

    public synchronized void main(int i) {

        while (bShouldSub) {// 如果不轮到自己执行，就睡
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int j = 1; j <= 5; j++) {
            System.out.println("main thread sequence of " + j + ", loop of " + i);
        }

        bShouldSub = true;// 改变标记
        this.notify();// 唤醒正在等待的子线程
    }
}
