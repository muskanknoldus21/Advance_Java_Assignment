package adv.q3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SignalandSignalAll {
    public static void main(String[] args) {

        ProducerConsumerImpl sharedObject = new ProducerConsumerImpl ();

        Producer p = new Producer(sharedObject);
        Consumer c = new Consumer(sharedObject);

        p.start();
        c.start();
    }
}

class ProducerConsumerImpl {

    private static final int CAPACITY = 10;
    private final Queue queue = new LinkedList<>();
    private final Random random = new Random();

    private final Lock lock = new ReentrantLock();
    private final Condition bufferNotFull = lock.newCondition();
    private final Condition bufferNotEmpty = lock.newCondition();

    public void put () throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == CAPACITY) {
                System.out.println(Thread.currentThread().getName() + " : Buffer is full, waiting");
                bufferNotEmpty.await();
            }

            int num = random.nextInt();
            boolean isAdded = queue.offer(num);
            if (isAdded) {
                System.out.printf("%s added %d into queue %n", Thread
                        .currentThread().getName(), num);

                System.out.println(Thread.currentThread().getName()
                        + " : Signalling that buffer is no more empty now");
                bufferNotFull.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void get() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println(Thread.currentThread().getName()
                        + " : Buffer is empty, waiting");
                bufferNotFull.await();
            }

            Integer value = Integer.parseInt(queue.poll().toString());
            if (value != null) {
                System.out.printf("%s consumed %d from queue %n", Thread
                        .currentThread().getName(), value);

                // signal producer thread that, buffer may be empty now
                System.out.println(Thread.currentThread().getName()
                        + " : Signalling that buffer may be empty now");
                bufferNotEmpty.signalAll();
            }

        } finally {
            lock.unlock();
        }
    }
}

class Producer extends Thread {
    ProducerConsumerImpl pc;

    public Producer(ProducerConsumerImpl sharedObject) {
        super("PRODUCER");
        this.pc = sharedObject;
    }

    @Override
    public void run() {
        try {
            pc.put();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread {
    ProducerConsumerImpl pc;

    public Consumer(ProducerConsumerImpl sharedObject) {
        super("CONSUMER");
        this.pc = sharedObject;
    }

    @Override
    public void run() {
        try {
            pc.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
