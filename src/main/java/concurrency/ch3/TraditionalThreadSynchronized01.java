package concurrency.ch3;

/**
 * Created by liuxiwen on 2017/2/28.
 */
public class TraditionalThreadSynchronized01 {

    public static void main(String[] args) {

        new TraditionalThreadSynchronized01().init();
    }

    private void init() {

        final Outputer outputer = new Outputer();

        // 线程1打印：duoxiancheng
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output1("duoxiancheng");
                }
            }
        }).start();

        // 线程2打印：liuxiwen
        new Thread(new Runnable() {

            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output1("liuxiwen");
                }
            }
        }).start();
    }

    static class Outputer {

        // 自定义一个字符串打印方法，一个个字符的打印
        public void output1(String name) {

            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println("");
        }
    }
}
