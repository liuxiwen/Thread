package concurrency.ch2;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liuxiwen on 2017/2/28.
 */
public class TraditionalTimer02 {

    public static void main(String[] args) {

        // 自定义一个定时器任务
        class MyTimerTask extends TimerTask {

            @Override
            public void run() {
                System.out.println("--boom--");
                // 任务执行完再装一个定时器，扔进自定义的定时器任务
                new Timer().schedule(new MyTimerTask(), 3000);
            }
        }

        new Timer().schedule(new MyTimerTask(), 4000);// 外面开启定时器

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
