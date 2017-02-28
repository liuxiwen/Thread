package concurrency.ch2;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liuxiwen on 2017/2/27.
 */
public class TraditionalTimer01 {

    public static void main(String[] args) {

        // 简单定时器demo
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // 实际中会扔一个对象进来，在这里就可以操作该对象的所有方法了
                System.out.println("--boom--");
            }
        }, 2000, 3000);

        // 打印秒钟，一次输出一次，方便观察
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
