package org.example.reentrantReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {
    private double balance = 1000;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public double viewBalance(String user){
        readLock.lock();
        try {
            System.out.println(user+" is checking balance");
            Thread.sleep(500);;
            System.out.println(balance+" is the balance");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            readLock.unlock();
            return balance;
        }
    }

    public double withdraw(int amount,String user){
        if(readWriteLock.getReadLockCount() > 0) {
            System.out.println(user + " is waiting for readers to release the lock. Readers count: " + readWriteLock.getReadLockCount());
        }
        if(readWriteLock.isWriteLocked()) {
            System.out.println(user + " is waiting for another writer to release the lock.");
        }
        writeLock.lock();
        try {
            System.out.println("Amount is withdrawn "+amount);
            Thread.sleep(500);;
            if(balance >= amount) {
                balance -= amount;
                System.out.println("Amount is withdrawn successfully by "+user);
            }else{
                System.out.println(user + " tried to withdraw $" + amount + ", but insufficient funds.");
            }
            return balance;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            writeLock.unlock();
        }
    }
}