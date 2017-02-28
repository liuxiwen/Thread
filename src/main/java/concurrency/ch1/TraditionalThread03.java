package concurrency.ch1;

/**
 * Created by liuxiwen on 2017/2/27.
 */
public class TraditionalThread03 {

    public static void main(String[] args) {

        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Runnable:" + Thread.currentThread().getName());
            }
        }) {

            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread:" + Thread.currentThread().getName());
            }
        }.start();
    }
}
