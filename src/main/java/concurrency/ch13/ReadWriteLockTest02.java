package concurrency.ch13;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liuxiwen on 2017/3/6.
 */
public class ReadWriteLockTest02 {

    public static void main(String[] args) {

        CacheData cache = new CacheData();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                cache.processCache();// 都去拿数据
            }).start();
        }
    }
}

class CacheData {

    private Object data = null;// 需要缓存的数据
    private boolean cacheValid;// 用来标记是否有缓存数据
    private ReadWriteLock rwl = new ReentrantReadWriteLock();// 定义读写锁

    public void processCache() {

        rwl.readLock().lock();// 上读锁
        if (!cacheValid) {// 如果没有缓存，那说明是第一次访问，需要给data赋个值
            rwl.readLock().unlock();// 先把读锁释放掉
            rwl.writeLock().lock();// 上写锁
            if (!cacheValid) {
                System.out.println(Thread.currentThread().getName() + ": no cache!");
                data = new Random().nextInt(1000);// 赋值
                cacheValid = true;// 标记已经有缓存了
                System.out.println(Thread.currentThread().getName() + ": already cached!");
            }
            rwl.readLock().lock();// 再把读锁上上
            rwl.writeLock().unlock();// 把刚刚上的写锁释放掉
        }
        System.out.println(Thread.currentThread().getName() + " get data: " + data);
        rwl.readLock().unlock();// 释放读锁
    }
}