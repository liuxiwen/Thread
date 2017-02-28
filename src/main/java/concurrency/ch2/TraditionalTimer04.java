package concurrency.ch2;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liuxiwen on 2017/2/28.
 */
public class TraditionalTimer04 {

    public static void main(String[] args) {

        new Timer().schedule(new MyTimerTaskA(), 2000);// A和B随便开一个

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

// 自定义两个定时器任务类，集成TimerTask即可
class MyTimerTaskA extends TimerTask {

    @Override
    public void run() {

        System.out.println("--boomA--");
        new Timer().schedule(new MyTimerTaskB(), 4000);
    }
}

class MyTimerTaskB extends TimerTask {

    @Override
    public void run() {

        System.out.println("--boomB--");
        new Timer().schedule(new MyTimerTaskA(), 2000);
    }
}
