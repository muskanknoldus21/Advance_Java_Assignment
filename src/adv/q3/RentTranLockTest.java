package adv.q3;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RentTranLockTest {
    public static void main(String[] args) throws Exception {

        final Runner runner = new Runner();
        Thread thread1 = new Thread(new Runnable() {
//            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
//            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while (true) {

            boolean gotFirstLock = false;
            boolean gotSecondLock = false;
            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
        }
        finally {
            if (gotFirstLock && gotSecondLock) {
                return;
            }
            if (gotFirstLock) {
                firstLock.unlock();
            }
            if (gotSecondLock) {
                secondLock.unlock();
            }
        }
        Thread.sleep(5);
    }

}
    public void firstThread() throws InterruptedException {

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());
        System.out.println("Total balance: "
                + (account1.getBalance() + account2.getBalance()));
    }
}

class Account {
    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        if(balance >= amount){
            balance -= amount;
        }
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account account1, Account account2, int amount) {
        account1.withdraw(amount);
        account2.deposit(amount);
    }
}
