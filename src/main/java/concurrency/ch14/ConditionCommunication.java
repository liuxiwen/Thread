package concurrency.ch14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuxiwen on 2017/3/7.
 */
public class ConditionCommunication {

    public static void main(String[] args) {

        Business business = new Business();
        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub(i);
            }
        }).start();

        // main方法主线程
        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }
    }
}

class Business {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();// Condition实在具体的Lock之上的
    boolean bShouldSub = true;

    public void sub(int i) {

        lock.lock();
        try {
            while (!bShouldSub) {
                try {
                    condition.await();// 用Condition来调用await()方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("sub thread sequence of " + j + ", loop of " + i);
            }
            bShouldSub = false;
            condition.signal();// 用Condition来发出唤醒信号，唤醒某一个
        } finally {
            lock.unlock();
        }
    }

    public void main(int i) {

        lock.lock();
        try {
            while (bShouldSub) {
                try {
                    condition.await();// 用Condition来调用await()方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("main thread sequence of " + j + ", loop of " + i);
            }
            bShouldSub = true;
            condition.signal();// 用Condition来发出唤醒信号，唤醒某一个
        } finally {
            lock.unlock();
        }
    }
}
