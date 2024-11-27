package com.RealTimeEventTicketingSystem.Server.Model;

public class Customer implements Runnable {

    private int customerID;
    private TicketPool ticketPool;
    private int customerRetrievalRate;

    public Customer(int customerID, TicketPool ticketPool, int customerRetrievalRate) {
        this.customerID = customerID;
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }


    @Override
    public void run() {

        System.out.println("Customer " + customerID + " is attempting to retrieve tickets from the pool.");

        while(true){

            try {
                for(int i = 0; i < customerRetrievalRate; i++){ //
                    String ticket = ticketPool.removeTicket("Ticket");

                    if(ticket != null){
                        System.out.println("Customer " + customerID + " has retrieved ticket: " + ticket);
                    }
                    else{
                        System.out.println("Customer " + customerID + " could not retrieve ticket.");
                    }
                }
                Thread.sleep(1000);
                // In this block of code, in each second the customer retrieves "customerRetrievalRate" number of tickets from the pool.

            } catch (InterruptedException e) {
                e.printStackTrace();

                System.out.println("Customer " + customerID + " has stopped retrieving tickets from the pool.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
