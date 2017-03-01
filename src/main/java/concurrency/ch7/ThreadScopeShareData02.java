package concurrency.ch7;

import java.util.Random;

/**
 * Created by liuxiwen on 2017/3/1.
 */
public class ThreadScopeShareData02 {

    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int data = new Random().nextInt();
                System.out.println(Thread.currentThread().getName() + " has put a data: " + data);
                // 每个线程维护一个User，User中保存了name和age
                User user = new User();
                user.setName("name" + data);
                user.setAge(data);
                threadLocal.set(user);// 向当前线程中存入User对象
                new TestA().getData();
                new TestB().getData();
            }).start();
        }
    }

    static class TestA {

        public void getData() {

            User user = threadLocal.get();// 从当前线程中取出User对象
            System.out.println("A get data from " + Thread.currentThread().getName() + ": " + user.getName() + ", " + user.getAge());
        }
    }

    static class TestB {

        public void getData() {

            User user = threadLocal.get();// 从当前线程中取出User对象
            System.out.println("B get data from " + Thread.currentThread().getName() + ": " + user.getName() + ", " + user.getAge());
        }
    }
}

// 定义一个User类用来存储姓名和年龄
class User {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
