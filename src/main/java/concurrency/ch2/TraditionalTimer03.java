package concurrency.ch2;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liuxiwen on 2017/2/28.
 */
public class TraditionalTimer03 {

    private static int count = 0;// 记录爆炸的次数

    public static void main(String[] args) {

        class MyTimerTask extends TimerTask {

            @Override
            public void run() {

                count = (count + 1) % 2;// 结果只有0和1
                System.out.println("--boom--");
                new Timer().schedule(new MyTimerTask(), 2000 + 2000 * count);// 根据count结果设定新的定时时间
            }
        }
        new Timer().schedule(new MyTimerTask(), 2000);

        while (true) {

            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
