package concurrency.ch13;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liuxiwen on 2017/3/6.
 */
public class ReadWriteLockTest01 {

    public static void main(String[] args) {

        final Queue3 q3 = new Queue3();// 封装共享的数据、读写锁和待执行的任务的类

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    q3.put(new Random().nextInt(10000));
                }
            }).start();

            new Thread(() -> {
                while (true) {
                    q3.get();
                }
            }).start();
        }

    }
}

class Queue3 {

    private Object data = null;// 共享的数据
    private ReadWriteLock rwl = new ReentrantReadWriteLock();// 定义读写锁

    // 读取数据的任务方法
    public void get() {

        rwl.readLock().lock();// 上读锁
        try {
            System.out.println(Thread.currentThread().getName() + " :before read: " + data);// 读之前打印数据显示
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + " :after read: " + data);// 读之后打印数据显示
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();// 释放读锁
        }
    }

    // 写数据的任务方法
    public void put(Object data) {

        rwl.writeLock().lock();// 上写锁
        try {
            System.out.println(Thread.currentThread().getName() + " :before read: " + data);// 读之前打印数据显示
            Thread.sleep((long) (Math.random() * 1000));
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " :after read: " + data);// 读之后打印数据显示
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();// 释放写锁
        }
    }
}