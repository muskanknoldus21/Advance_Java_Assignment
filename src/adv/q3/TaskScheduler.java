package adv.q3;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    public static void main(String[] args) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Timer timer = new Timer();
        TimerTask printStar = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Printing Stars");
                for (int i=0;i<5;i++){
                    System.out.print("* ");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        };
        timer.schedule(printStar, new Date(),1000);


        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Using scheduleAtFixedRate():");
                for(int i=1; i<=10;i++)
                {
                    for (int j=0;j<i;j++){
                        System.out.print("*");}
                    System.out.println("");
                }
            };
        };
        timer.scheduleAtFixedRate(task2,1000,1000);


        System.out.println("By using scheduleWithFixedDelay()");
        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduleWithFixedDelay: " + new Date());
            }
        }, 1000, 10 , TimeUnit.SECONDS);
    }

}

