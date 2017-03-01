package concurrency.ch7;

import java.util.Random;

/**
 * Created by liuxiwen on 2017/3/1.
 */
public class ThreadScopeShareData01 {

    // 定义一个ThreadLocal
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int data = new Random().nextInt();
                System.out.println(Thread.currentThread().getName() + " has put a data: " + data);
                threadLocal.set(data);// 直接往ThreadLocal里面放数据即可
                new TestA().getData();
                new TestB().getData();
            }).start();
        }
    }

    static class TestA {

        public void getData() {

            System.out.println("A get data from " + Thread.currentThread().getName() + ": " + threadLocal.get());// 直接取，不用什么关键字，它直接从当前线程中取
        }
    }

    static class TestB {

        public void getData() {

            System.out.println("B get data from " + Thread.currentThread().getName() + ": " + threadLocal.get());// 直接取，不用什么关键字，它直接从当前线程中取
        }
    }
}
