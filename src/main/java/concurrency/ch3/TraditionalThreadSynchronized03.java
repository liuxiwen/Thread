package concurrency.ch3;

/**
 * Created by liuxiwen on 2017/2/28.
 */
public class TraditionalThreadSynchronized03 {

    public static void main(String[] args) {

        new TraditionalThreadSynchronized03().init();
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
                    outputer.output2("liuxiwen");
                }
            }
        }).start();
    }

    static class Outputer {

        // 自定义一个字符串打印方法，一个个字符的打印
        public void output1(String name) {

            synchronized (this) {// 任何一个对象都可以作为参数，但是该对象对于两个线程来说是同一个才行
                int len = name.length();
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println("");
            }
        }

        /*synchronized关键字修饰方法的时候，同步锁是this，即等效于代码块synchronized(this){...}*/
        public synchronized void output2(String name) {

            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println("");
        }
    }
}
