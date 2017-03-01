package concurrency.ch6;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by liuxiwen on 2017/3/1.
 */
public class ThreadScopeShareData02 {

    private static int data = 0;// 公共的数据
    // 定义一个Map以键值对的方式存储每个线程和它对应的数据，即Thread：data
    private static Map<Thread, Integer> threadData = Collections.synchronizedMap(new HashMap<Thread, Integer>());

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int temp = new Random().nextInt();
                System.out.println(Thread.currentThread().getName() + " has put a data: " + temp);// 打印出来为了看效果
                threadData.put(Thread.currentThread(), temp);// 向Map中存入本线程data数据的一个副本
                data = temp;// 操作数据：赋新值
                new TestA().getData();
                new TestB().getData();
            }).start();
        }
    }

    static class TestA {

        public void getData() {

            System.out.println("A get data from " + Thread.currentThread().getName() + ": " + threadData.get(Thread.currentThread()));// 取出各线程维护的那个副本
        }
    }

    static class TestB {

        public void getData() {

            System.out.println("B get data from " + Thread.currentThread().getName() + ": " + threadData.get(Thread.currentThread()));
        }
    }
}
