package concurrency.ch13;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liuxiwen on 2017/3/6.
 */
public class CacheDemo {

    public static void main(String[] args) {

        Cache cac = new Cache();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                String value = (String) cac.getData("cache1");// 第一个进入的线程要先写一个数据进去（相当于第一次从数据库中取）
                System.out.println(Thread.currentThread().getName() + " : " + value);
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                String value = (String) cac.getData("cache2");// 第一个进入的线程要先写一个数据进去（相当于第一次从数据库中取）
                System.out.println(Thread.currentThread().getName() + " : " + value);
            }).start();
        }
    }
}

class Cache {

    // 存储缓存数据的Map，注意HashMap是非线程安全的，也要进行同步操作
    private Map<String, Object> cache = Collections.synchronizedMap(new HashMap<String, Object>());
    private ReadWriteLock rwl = new ReentrantReadWriteLock();// 定义读写锁

    public synchronized Object getData(String key) {

        rwl.readLock().lock();// 上读锁
        Object value = null;
        try {
            value = cache.get(key);// 根据key从缓存中拿数据
            if (value == null) {// 如果第一次那该key对应的数据拿不到
                rwl.readLock().unlock();// 释放读锁
                rwl.writeLock().lock();// 换成写锁
                try {
                    if (value == null) {// 之所以再去判断，是为了防止几个线程同时进入了上面的那个if，然后一个个都来重写赋值一遍
                        System.out.println(Thread.currentThread().getName() + " write cache for " + key);
                        value = "aaa" + System.currentTimeMillis();// 实际中是去数据库中取，这里只是模拟
                        cache.put(key, value);// 放到缓存中
                        System.out.println(Thread.currentThread().getName() + " has already written cache!");
                    }
                } finally {
                    rwl.writeLock().unlock();// 写完了释放写锁
                }
                rwl.readLock().lock();// 换读锁
            }
        } finally {
            rwl.readLock().unlock();// 最后释放读锁
        }
        return value;// 返回要取的数据
    }
}
