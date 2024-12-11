package com.RealTimeEventTicketingSystem.Server.Model;

import com.RealTimeEventTicketingSystem.Server.Config.TicketWebSocketHandler;

import java.util.logging.Logger;

public class Customer implements Runnable {

    private  final Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

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

        logger.info("Customer " + customerID + " is attempting to retrieve tickets from the pool.");

        int ticketsPurchased = 0;

        // Loop until the customer has purchased the desired number of tickets
        while(ticketsPurchased < purchasingTicketCount) {

            try {
                // Determine how many tickets to try purchasing in this round
                // The customer can retrieve a maximum of 'customerRetrievalRate' tickets per attempt,
                // but cannot exceed the number of remaining tickets needed.
                int ticketsToPurchaseNow = Math.min(customerRetrievalRate, purchasingTicketCount - ticketsPurchased);

                // Attempt to remove the tickets from the pool. If successful, increment the purchased count.
                if(ticketPool.removeTicket(ticketsToPurchaseNow)) {

                    ticketsPurchased += ticketsToPurchaseNow;

                    // Simulate the time delay of the customer's ticket retrieval process (1 second)
                    Thread.sleep(1000);
                    // In this block of code, in each second the customer retrieves "customerRetrievalRate" number of tickets from the pool.
                } else break;

            } catch (InterruptedException e) {

                logger.severe("Customer " + customerID + " has stopped retrieving tickets from the pool: " + e.getMessage());

                // Ensure that the interrupt flag is set, allowing the thread to exit properly
                Thread.currentThread().interrupt();
                break;
            }
        }

        if (ticketsPurchased > 0) {
            logger.info("Customer " + customerID +  " has successfully retrieved " + ticketsPurchased +  " tickets from the pool.");
        } else {
            // Log a warning if no tickets were retrieved
            logger.warning("Customer " + customerID + " could not retrieve any tickets.");
        }
    }


}
