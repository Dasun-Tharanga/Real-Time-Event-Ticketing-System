package com.RealTimeEventTicketingSystem.Server.Model;

import com.RealTimeEventTicketingSystem.Server.Service.ConfigService;


public class Customer implements Runnable {

    private final String customerID;
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private  int purchasingTicketCount;


    public Customer(String customerID, int purchasingTicketCount ,TicketPool ticketPool, int customerRetrievalRate) {
        this.customerID = customerID;
        this.purchasingTicketCount = purchasingTicketCount;
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }


    @Override
    public void run() {

        System.out.println("Customer " + customerID + " is attempting to retrieve tickets from the pool.");

        int ticketsPurchased = 0;

        while(ticketsPurchased < purchasingTicketCount) {

            try {
                int ticketsToPurchaseNow = Math.min(customerRetrievalRate, purchasingTicketCount - ticketsPurchased);

                if(ticketPool.removeTicket(ticketsToPurchaseNow)) {

                    ticketsPurchased += ticketsToPurchaseNow;

                    Thread.sleep(1000);
                    // In this block of code, in each second the customer retrieves "customerRetrievalRate" number of tickets from the pool.
                } else break;

            } catch (InterruptedException e) {
                e.printStackTrace();

                System.out.println("Customer " + customerID + " has stopped retrieving tickets from the pool.");
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Customer " + customerID + " has successfully retrieved " + ticketsPurchased + " tickets from the pool.");
    }


}
