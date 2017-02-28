package concurrency.ch1;

/**
 * Created by liuxiwen on 2017/2/27.
 */
public class TraditionalThread02 {

    public static void main(String[] args) {


        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        });

        thread1.start();
    }
}
