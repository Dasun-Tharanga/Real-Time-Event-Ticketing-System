package com.RealTimeEventTicketingSystem.Server.Service;

import com.RealTimeEventTicketingSystem.Server.Model.TicketPool;
import com.RealTimeEventTicketingSystem.Server.Model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TicketService {

    private static final Logger logger = Logger.getLogger(TicketService.class.getName());

    private final TicketPool ticketPool;

    @Autowired
    public TicketService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    /**
     * Method to add tickets to the ticket pool.
     * @param count The number of tickets to add.
     * @return A message indicating whether the tickets were successfully added or not.
     */
    public String addTickets(int count) {
        try {
            // Attempt to add the tickets to the pool
            if (ticketPool.addTickets(count)) {
                logger.info(count + " tickets successfully added to the pool.");
                return count + " tickets successfully added.";
            } else {
                // Log if adding tickets failed due to exceeding max capacity
                logger.warning("Failed to add " + count + " tickets. Exceeds max capacity.");
                return "Failed to add tickets. Exceeds max capacity.";
            }
        } catch (Exception e) {
            // Log any unexpected errors
            logger.log(Level.SEVERE, "Error adding tickets to the pool.", e);
            return "Error adding tickets to the pool.";
        }
    }

    /**
     * Method to purchase a ticket from the pool.
     * @param ticket The number of tickets the customer wants to purchase.
     * @return A message indicating whether the purchase was successful or not.
     */
    public String purchaseTicket(int ticket) {
        try {
            // Attempt to remove the ticket from the pool
            if (ticketPool.removeTicket(ticket)) {
                logger.info("Successfully purchased " + ticket + " ticket(s).");
                return "Successfully purchased " + ticket;
            } else {
                // Log and return a message if ticket is not available
                logger.warning("Ticket " + ticket + " not available or already sold.");
                return "Ticket not available or already sold.";
            }
        } catch (Exception e) {
            // Log any unexpected errors
            logger.log(Level.SEVERE, "Error processing ticket purchase.", e);
            return "Error processing ticket purchase.";
        }
    }

    /**
     * Method to get the number of available tickets in the pool.
     * @return The number of available tickets.
     */
    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets();
    }



}
