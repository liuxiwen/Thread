package concurrency.ch4;

import java.util.Objects;

/**
 * Created by liuxiwen on 2017/2/28.
 */
public class DeadLock02 {

    public static void main(String[] args) {

        // 开启两个线程，分别扔两个自定义的Runnable进去
        new Thread(new MyRunnable(true)).start();
        new Thread(new MyRunnable(false)).start();
    }
}

class MyRunnable implements Runnable {

    private boolean flag;// 用于判断，执行不同的同步代码块

    MyRunnable(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {

        if (flag) {
            while (true) {
                synchronized (MyLock.lock_a) {
                    System.out.println("--ThreadA--lock_a--");
                    synchronized (MyLock.lock_b) {
                        System.out.println("--ThreadA--lock_b--");
                    }
                }
            }
        } else {
            while (true) {
                synchronized (MyLock.lock_b) {
                    System.out.println("--ThreadB--lock_b--");
                    synchronized (MyLock.lock_a) {
                        System.out.println("--ThreadB--lock_b--");
                    }
                }
            }
        }
    }
}

class MyLock {// 把两把锁放到一个类中定义，是为了两个线程使用的都是这两把锁

    public static final Object lock_a = new Object();
    public static final Object lock_b = new Object();
}
