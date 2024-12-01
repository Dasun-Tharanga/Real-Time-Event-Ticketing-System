package com.RealTimeEventTicketingSystem.Server.Service;

import com.RealTimeEventTicketingSystem.Server.Model.TicketPool;
import com.RealTimeEventTicketingSystem.Server.Model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final TicketPool ticketPool;

    @Autowired
    public TicketService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public String addTickets(int count) {
        if (ticketPool.addTickets(count)) {
            return count + " tickets successfully added.";
        } else {
            return "Failed to add tickets. Exceeds max capacity.";
        }
    }

    public String purchaseTicket(int ticket) {
        if (ticketPool.removeTicket(ticket)) {
            return "Successfully purchased " + ticket;
        } else {
            return "Ticket not available or already sold.";
        }
    }

    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets();
    }



}
