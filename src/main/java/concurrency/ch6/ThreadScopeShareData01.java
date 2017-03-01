package concurrency.ch6;

import java.util.Random;

/**
 * Created by liuxiwen on 2017/3/1.
 */
public class ThreadScopeShareData01 {

    private static int data = 0;// 公共的数据

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int temp = new Random().nextInt();
                System.out.println(Thread.currentThread().getName() + " has put a data: " + temp);// 打印出来为了看效果
                data = temp;// 操作数据：赋新值
                new TestA().getData();
                new TestB().getData();
            }).start();
        }
    }

    static class TestA {

        public void getData() {

            System.out.println("A get data from " + Thread.currentThread().getName() + ": " + data);// 取出公共数据data
        }
    }

    static class TestB {

        public void getData() {

            System.out.println("B get data from " + Thread.currentThread().getName() + ": " + data);
        }
    }
}
