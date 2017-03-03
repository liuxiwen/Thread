package concurrency.ch9;

/**
 * Created by liuxiwen on 2017/3/3.
 */
public class MultiThreadShareData02 {

    public static void main(String[] args) {

        ShareData2 task = new ShareData2();// 公共数据和任务放在task中

        for (int i = 0; i < 2; i++) {// 开启两个线程增加data
            new Thread(() -> task.increment()).start();
        }

        for (int i = 0; i < 2; i++) {// 开启两个线程减少data
            new Thread(() -> task.decrement()).start();
        }
    }
}

class ShareData2 {

    private int data = 0;

    public synchronized void increment() {// 增加data

        System.out.println(Thread.currentThread().getName() + ": before: " + data);
        data++;
        System.out.println(Thread.currentThread().getName() + ": after: " + data);
    }

    public synchronized void decrement() {// 减少data

        System.out.println(Thread.currentThread().getName() + ": before: " + data);
        data--;
        System.out.println(Thread.currentThread().getName() + ": after: " + data);
    }
}
