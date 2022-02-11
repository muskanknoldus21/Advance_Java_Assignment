package adv.q3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadExecuterTest implements Runnable{
    String name;
    ThreadExecuterTest(String name){
        this.name = name;
    }
    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.println("Hello "+name);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]){
        ThreadExecuterTest threadTest1 = new ThreadExecuterTest("Utkarsh");
        Thread thread1 = new Thread(threadTest1);
        thread1.setName("Thread-1");

        ThreadExecuterTest threadTest2 = new ThreadExecuterTest("Lucifer");
        Thread thread2 = new Thread(threadTest2);
        thread2.setName("Thread-2");

        ThreadExecuterTest threadTest3 = new ThreadExecuterTest("Predator");
        Thread thread3 = new Thread(threadTest3);
        thread3.setName("Thread-3");


        // Creating a fixed thread pool with maximum one thread.
        ExecutorService executors = Executors.newFixedThreadPool(3);


        thread1.start();
        //Using join method
        try {
            thread1.join(5000);
            System.out.println("Starts other threads after when first thread "+thread1.getName()+" has died. Waiting by join method.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executors.submit(thread2);
        executors.submit(thread3);
        System.out.println("Is shutdown :"+executors.isShutdown());
        System.out.println("Is Terminated :"+executors.isTerminated());
        for (int i =0; i<3; i++){
            System.out.println("Good Evening");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executors.submit(()->{
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("interrupted");
                    break;
                }
            }
        });


        System.out.println("***********Waiting for tasks to be complete*********");

//        Using shutdown method
        executors.shutdown();
        try {
            if (!executors.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                System.out.println("Terminating all the tasks");
                executors.shutdownNow();
            }
        } catch (InterruptedException e) {
            executors.shutdownNow();
        }

//        Using isShutDown() method
        while (!executors.isShutdown());
        System.out.println("By shutdown() method, it can't accept new tasks");

        // Waiting for all thread to finish
        while (!executors.isTerminated());
        System.out.println("Is Terminated :"+executors.isTerminated());

    }
}
