package org.example.reentrantlock_sync;

import java.util.ArrayList;
import java.util.List;

public class TicketBookingApp {
    public static void main(String[] args) throws InterruptedException {
        TicketCounter counter = new TicketCounter();
        String[] users = {"mahe", "manish", "Priya", "Aarohi", "Abhigna", "Ankitha", "venu", "sai", "vam", "mahi"};

        List<Thread> threads = new ArrayList<>();
        for (String user : users) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    counter.bookTicket(user);
                }
            });
            t.start();
            threads.add(t);
        }

//      Without join(), your main program can terminate early, causing unfinished work or incomplete console output.
//      With join(), you ensure all concurrent tasks finish before main exits — this is important for correctness and predictable program behavior.
        for (Thread t : threads) {
            t.join();
        }
    }
}
