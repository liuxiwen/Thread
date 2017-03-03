package concurrency.ch9;

/**
 * Created by liuxiwen on 2017/3/3.
 */
public class MultiThreadShareData01 {

    public static void main(String[] args) {

        ShareData task = new ShareData();// 一个类实现了Runnable接口

        for (int i = 0; i < 4; i++) {// 四个线程来卖票
            new Thread(task).start();
        }
    }
}

class ShareData implements Runnable {

    private int data = 100;

    @Override
    public void run() {// 卖票，每次一个线程进来，先判断票数是否大于0

        while (data > 0) {
            synchronized (this) {
                if (data > 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + data);
                    data--;
                }
            }
        }
    }
}
