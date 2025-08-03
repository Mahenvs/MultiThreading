package org.example.reentrantlock_sync;

public class TicketCounter {
    private int ticketsAvailable = 5;

    public void bookTicket(String user){
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
        }

    }
}
