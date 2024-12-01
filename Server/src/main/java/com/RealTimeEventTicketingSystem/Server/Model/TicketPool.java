package com.RealTimeEventTicketingSystem.Server.Model;

import com.RealTimeEventTicketingSystem.Server.Service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TicketPool {

    private final List<String> ticketPool ;
    private int totalTickets ;
    private int soldTickets = 0;

    @Autowired
    public TicketPool(ConfigService configService) {
        this.ticketPool = Collections.synchronizedList(new ArrayList<>());
        this.totalTickets = configService.getConfig().getTotalTickets();

    }


    public synchronized boolean addTickets(int ticketCount) {

        if((ticketPool.size() + soldTickets + ticketCount) > totalTickets) {
            System.out.println("Can't add more tickets to the pool");
            return false;
        }
        for (int i = 0; i < ticketCount; i++) {
            ticketPool.add("Ticket");
        }

        System.out.println("Tickets added to the pool: " + ticketCount);
        return true;
    }

    public synchronized boolean removeTicket(int ticketCount) {

        if (ticketPool.size() < ticketCount || ticketPool.isEmpty()) {
            System.out.println("Ticket pool doesn't contain " + ticketCount + " tickets.");
            return false;
        }

        for (int i = 0; i < ticketCount; i++) {
            ticketPool.remove("Ticket");
        }
        System.out.println("Ticket removed from the pool: " + ticketCount);

        return true;


    }

    public synchronized List<String> getTickets() {
        return null;
    }

    public int getAvailableTickets() {
        return ticketPool.size();
    }




}
