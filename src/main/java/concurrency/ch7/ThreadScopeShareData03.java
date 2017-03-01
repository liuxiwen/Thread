package concurrency.ch7;

import java.util.Random;

/**
 * Created by liuxiwen on 2017/3/1.
 */
public class ThreadScopeShareData03 {

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {

            new Thread(() -> {
                int data = new Random().nextInt();
                System.out.println(Thread.currentThread().getName() + " has put a data: " + data);
                // 这里直接用User2去调用getThreadLocal这个静态方法获取本线程范围内的一个User2对象
                // 这样就优雅多了，完全不用关心如何去拿该线程中的对象，如何把对象放到ThreadLocal中
                // 只要拿就行，而且拿出来的肯定就是当前线程中的对象
                User2.getThreadInstance().setName("name" + data);
                User2.getThreadInstance().setAge(data);
                new TestA().getData();
                new TestB().getData();
            }).start();
        }
    }

    static class TestA {

        public void getData() {

            // 还是调用这个静态方法拿，因为刚刚已经拿过一次了，ThreadLocal中已经有了
            User2 user2 = User2.getThreadInstance();
            System.out.println("A get data from " + Thread.currentThread().getName() + ": " + user2.getName() + ", " + user2.getAge());
        }
    }

    static class TestB {

        public void getData() {

            User2 user2 = User2.getThreadInstance();
            System.out.println("B get data from " + Thread.currentThread().getName() + ": " + user2.getName() + ", " + user2.getAge());
        }
    }
}

class User2 {

    private User2() {}

    private static ThreadLocal<User2> threadLocal = new ThreadLocal<>();

    // 注意，这不是单例，每个线程都可以new，所以不用synchronized，但是每个ThreadLocal中是单例的，因为有了的话就不会再new了
    public static User2 getThreadInstance() {

        User2 instance = threadLocal.get();// 先从当前ThreadLocal中拿
        if (instance == null) {
            instance = new User2();
            threadLocal.set(instance);// 如果没有就新new一个放到ThreadLocal中
        }
        return instance;// 向外返回该User
    }

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
