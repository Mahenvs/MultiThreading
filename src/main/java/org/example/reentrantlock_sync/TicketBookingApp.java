package org.example.reentrantlock_sync;

public class TicketBookingApp {
    public static void main(String[] args) throws InterruptedException {
        TicketCounter counter = new TicketCounter();
        String[] users = {"mahe","manish","venu","sai","vam","mahi","mahesh"};
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(String user: users){
                    counter.bookTicket(user);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(String user: users){
                    counter.bookTicket(user);
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
