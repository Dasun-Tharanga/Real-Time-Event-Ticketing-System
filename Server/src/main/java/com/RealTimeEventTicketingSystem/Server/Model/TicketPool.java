package com.RealTimeEventTicketingSystem.Server.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {

    private final List<String> ticketPool;
    private final int maxTicketCapacity;
    private final int totalTickets;


    public TicketPool(int maxTicketCapacity, int totalTickets) {
        this.ticketPool = Collections.synchronizedList(new ArrayList<>());
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
    }


    public synchronized void addTickets(int ticketCount) {
        for (int i = 0; i < ticketCount; i++) {

            if(ticketPool.size()  >= maxTicketCapacity){
                System.out.println("Ticket pool is full. Cannot add more tickets.");
                break;
            } else if(ticketPool.size() + ticketCount > maxTicketCapacity){
                System.out.println("Cannot add" + ticketCount + " tickets to the pool. Pool will exceed the maximum capacity.");
                break;
            }
            ticketPool.add("Ticket" + i);
        }

        System.out.println("Tickets added to the pool: " + ticketCount);
    }

    public synchronized String removeTicket(String ticket) {
        System.out.println("Ticket removed from the pool: " + ticket);

        if (ticketPool.contains(ticket)) {
            ticketPool.remove(ticket);

            return ticket;
        }
        else{
            System.out.println("Ticket not found in the pool.");
            return null;
        }

    }


}
