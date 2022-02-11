package adv.q3;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRate {
    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Started making food at :" + new Date());
            }
        }, 1, 3L, TimeUnit.SECONDS);

        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("Started making food at :" + new Date());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 3L, TimeUnit.SECONDS);

    }
}
