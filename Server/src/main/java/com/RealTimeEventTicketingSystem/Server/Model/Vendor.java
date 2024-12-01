package com.RealTimeEventTicketingSystem.Server.Model;

public class Vendor implements Runnable {

    private final String vendorID;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int totalTicketsToRelease;


    public Vendor(String vendorID, TicketPool ticketPool, int ticketReleaseRate, int totalTicketsToRelease) {
        this.vendorID = vendorID;
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.totalTicketsToRelease = totalTicketsToRelease;
    }

    @Override
    public void run() {

        System.out.println("Vendor " + vendorID + " is adding tickets to the pool.");

        int ticketsReleased = 0;

        while (ticketsReleased < totalTicketsToRelease) {

            try {
                int ticketsToReleaseNow = Math.min(ticketReleaseRate, totalTicketsToRelease - ticketsReleased);

                if(ticketPool.addTickets(ticketsToReleaseNow)) {

                    ticketsReleased += ticketsToReleaseNow;

                    Thread.sleep(500);
                    // In this block of code, in each second the vendor adds "ticketReleaseRate" number of tickets to the pool.

                }else break;

            } catch (InterruptedException e) {
                e.printStackTrace();

                System.out.println("Vendor " + vendorID + " has stopped adding tickets to the pool.");
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Vendor " + vendorID + " added " + ticketsReleased + " tickets to the pool.");
    }
}