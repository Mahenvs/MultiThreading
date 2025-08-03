package org.example.reentrantTryLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketBookingTryLockApp {
    public static void main(String[] args) throws InterruptedException {
        TicketCounter counter = new TicketCounter();
        String[] users = {"mahe", "manish", "Priya", "Aarohi", "Abhigna", "Ankitha", "venu", "sai", "vam", "mahi"};

        Random random = new Random();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String user = users[random.nextInt(users.length)];
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(random.nextDouble() < 0.7) {
                            counter.bookTicket(user);
                        }else{
                        counter.cancelTicket(user);
                    }
                }catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
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
