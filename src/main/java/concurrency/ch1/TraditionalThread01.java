package concurrency.ch1;

/**
 * Created by liuxiwen on 2017/2/27.
 */
public class TraditionalThread01 {

    public static void main(String[] args) {

        // 继承Thread类，覆写run()方法
        Thread thread1 = new Thread() {

            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());// 打印当前线程名
            }
        };
        thread1.start();
    }
}
