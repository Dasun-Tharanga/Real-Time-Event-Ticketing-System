package com.RealTimeEventTicketingSystem.Server.Model;

import java.util.logging.Logger;

public class Vendor implements Runnable {

    private static final Logger logger = Logger.getLogger(Vendor.class.getName());

    private final String vendorID;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int totalTicketsToRelease;
    private volatile boolean running = true; // Flag to control the running state of the vendor


    public Vendor(String vendorID, TicketPool ticketPool, int ticketReleaseRate, int totalTicketsToRelease) {
        this.vendorID = vendorID;
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.totalTicketsToRelease = totalTicketsToRelease;
    }

    @Override
    public void run() {

        logger.info("Vendor " + vendorID + " is adding tickets to the pool.");

        int ticketsReleased = 0;

        // The loop runs as long as the vendor is running and hasn't released all the tickets
        while (running && ticketsReleased < totalTicketsToRelease) {

            try {
                // Calculate how many tickets to release in the current cycle
                // The vendor can release a maximum of 'ticketReleaseRate' tickets per attempt,
                // but the number cannot exceed the remaining tickets to be released.
                int ticketsToReleaseNow = Math.min(ticketReleaseRate, totalTicketsToRelease - ticketsReleased);

                // Attempt to add tickets to the pool. If successful, increment the released count.
                if(ticketPool.addTickets(ticketsToReleaseNow)) {

                    ticketsReleased += ticketsToReleaseNow;

                    // Simulate the time delay of the vendor releasing tickets (1 minute between releases)
                    Thread.sleep(60000);
                    // In this block of code, in each second the vendor adds "ticketReleaseRate" number of tickets to the pool.

                }else break;// If the ticket addition fails (e.g., no space left), break out of the loop

            } catch (InterruptedException e) {

                // Log the interruption error and stop the thread if interrupted
                logger.severe("Vendor " + vendorID + " was interrupted while adding tickets: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
        logger.info("Vendor " + vendorID + " added " + ticketsReleased + " tickets to the pool.");
    }

    public void stop() {
        this.running = false;
    }
}