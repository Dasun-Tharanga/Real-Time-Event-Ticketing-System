package com.RealTimeEventTicketingSystem.Server.Model;

public class Vendor implements Runnable {

    private int vendorID;
    private TicketPool ticketPool;
    private int ticketReleaseRate;

    public Vendor(int vendorID, TicketPool ticketPool, int ticketReleaseRate) {
        this.vendorID = vendorID;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {

        System.out.println("Vendor " + vendorID + " is adding tickets to the pool.");

        while(true){

            try {
                ticketPool.addTickets(ticketReleaseRate);
                Thread.sleep(1000);
                // In this block of code, in each second the vendor adds "ticketReleaseRate" number of tickets to the pool.
            } catch (InterruptedException e) {
                e.printStackTrace();

                System.out.println("Vendor " + vendorID + " has stopped adding tickets to the pool.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}