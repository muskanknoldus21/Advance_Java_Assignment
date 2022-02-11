package adv.q3;

import java.util.concurrent.*;

public class CachedThreadPoolTest {
    public static void main(String[] args) {

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);

        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) executorService;

        System.out.println("Largest executions: " + poolExecutor.getLargestPoolSize());
        System.out.println("Max allowed threads: " + poolExecutor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + poolExecutor.getPoolSize());
        System.out.println("Currently executing threads: " +poolExecutor.getActiveCount());
        System.out.println("Total number of threads (till now scheduled): " + poolExecutor.getTaskCount());

        executorService.submit(new Task());
        executorService.submit(new Task());

        System.out.println("Core Threads: " +poolExecutor.getCorePoolSize());
        System.out.println("Largest Executions: " +poolExecutor.getLargestPoolSize());
        System.out.println("Max allowed threads: " +poolExecutor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + poolExecutor.getPoolSize());
        System.out.println("Currently executing threads: " + poolExecutor.getActiveCount());
        System.out.println("Total no of threads (till now scheduled) " +poolExecutor.getTaskCount());
        executorService.shutdown();

    }
}

class Task implements Runnable {
    public void run () {

        try {
            Long duration = (long) (Math.random() * 5);
            System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(duration);
            System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
