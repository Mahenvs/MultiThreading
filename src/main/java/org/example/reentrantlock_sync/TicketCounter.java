package org.example.reentrantlock_sync;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class TicketCounter {
    private int ticketsAvailable = 5;
    private final ReentrantLock lock = new ReentrantLock();
    private final Set<String> bookedUsers = new HashSet<>();

    public void bookTicket(String user){
        lock.lock(); //Acquire lock
        try{
            if(ticketsAvailable > 0 && !bookedUsers.contains(user)){
                System.out.println(user+" is booking a ticket");
                Thread.sleep(500);
                bookedUsers.add(user);
                ticketsAvailable--;
                System.out.println("Ticket booked successfully, left over tickets are: "+ticketsAvailable);
            }else if(bookedUsers.contains(user)){
                System.out.println("Already booked ticket for user "+user+" left over tickets are: "+ticketsAvailable);
            } else{
                System.out.println("Tickets are not available for user "+user);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
//            when we forgot to release the lock, can lead to deadlock or starvation
//            lock.unlock();//Always release lock
        }
    }

    public int cancelTicket(String user){
        lock.lock();
        try {
            if(bookedUsers.contains(user)) {
                ticketsAvailable += 1;
                System.out.println(user+ " cancelled Ticket. Available tickets: " + ticketsAvailable);
            }else{
                System.out.println(user+" don't have a ticket Nothing to cancel. ");
            }
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
        return this.ticketsAvailable;
    }
}
