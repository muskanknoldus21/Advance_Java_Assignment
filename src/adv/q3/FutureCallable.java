package adv.q3;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class FutureCallable implements Callable {
    @Override
    public String call () throws Exception {
        Thread.sleep(1000);

        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Future<String>> list = new ArrayList<Future<String>>();
        Callable<String> callable = new FutureCallable();

        ScheduledExecutorService executorService2 = Executors.newScheduledThreadPool(1);
        LocalDateTime now = LocalDateTime.now();

        System.out.println("Worker Tasks scheduled at :" + LocalDateTime.now());

        ScheduledFuture<String> result1 = executorService2.schedule(new WorkerTask("Worker Task - 1"), Duration.between(now, now.plusSeconds(2)).toMillis(), TimeUnit.MILLISECONDS);
        ScheduledFuture<String> result2 = executorService2.schedule(new WorkerTask("Worker Task - 2"), Duration.between(now, now.plusSeconds(2)).toMillis(), TimeUnit.MILLISECONDS);

        for (int i = 0; i < 20; i++) {
            Future<String> future = executorService2.submit(callable);
            list.add(future);
        }

        for (Future<String> future : list) {
            try {
                System.out.println(new Date() + "::" + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

        System.out.println("Task - 1 is done : " + result1.isDone());
        System.out.println("Task - 2 is done :" + result2.isDone());
        System.out.println("Waiting for tasks to be completed");

        try {
            executorService2.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result2.cancel(true);

        System.out.println("All Tasks are now completed");
        System.out.println("Task - 1 is done : " + result1.isDone());
        System.out.println("Task - 2 is done : " + result2.isDone());
        System.out.println("Task - 2 is cancelled : " + result2.isCancelled());

        System.out.println("Terminated Successfully");
    }
}
class WorkerTask implements Callable<String> {
    private final String name;
    public WorkerTask(String name) {
        this.name = name;
    }
    @Override
    public String call () throws Exception {

        System.out.println("WorkerTask [" + name + "] executed on : " + LocalDateTime.now().toString());
        return "WorkerTask [" + name + "] is SUCCESS !!";
    }

}
