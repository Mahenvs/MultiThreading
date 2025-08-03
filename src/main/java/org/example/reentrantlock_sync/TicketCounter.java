package org.example.reentrantlock_sync;

import java.util.concurrent.locks.ReentrantLock;

public class TicketCounter {
    private int ticketsAvailable = 5;
    private final ReentrantLock lock = new ReentrantLock();
    public void bookTicket(String user){
        lock.lock(); //Acquire lock
        try{
            if(ticketsAvailable > 0){
                System.out.println(user+" is booking a ticket");
                Thread.sleep(500);
                ticketsAvailable--;
                System.out.println("Ticket booked successfully, left over tickets are: "+ticketsAvailable);
            }else{

                System.out.println("Tickets are not available for user "+user);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
            lock.unlock();//Always release lock
        }

    }
}
