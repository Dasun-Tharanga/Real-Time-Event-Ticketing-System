package com.RealTimeEventTicketingSystem.Server.Model;

import com.RealTimeEventTicketingSystem.Server.Config.TicketWebSocketHandler;
import com.RealTimeEventTicketingSystem.Server.Service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Component
public class TicketPool {

    private  final Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    private final List<String> ticketPool ;
    private final TicketWebSocketHandler ticketWebSocketHandler;
    private int totalTickets ;
    private int soldTickets = 0;

    @Autowired
    public TicketPool(ConfigService configService, TicketWebSocketHandler ticketWebSocketHandler) {
        this.ticketPool = Collections.synchronizedList(new ArrayList<>());
        this.totalTickets = configService.loadConfig().getTotalTickets();
        this.ticketWebSocketHandler = ticketWebSocketHandler;

    }

    /**
     * Adds tickets to the pool. It ensures that the total number of tickets does not exceed the configured limit.
     *
     * @param ticketCount The number of tickets to add.
     * @return true if tickets were successfully added, false otherwise.
     */
    public synchronized boolean addTickets(int ticketCount) {

        // Check if addng the tickets exceeds the total available tickets
        if((ticketPool.size() + soldTickets + ticketCount) > totalTickets) {
            logger.warning("Cannot add more tickets. Ticket pool would exceed the total allowed tickets.");
            return false;
        }

        // Adding the tickets to the pool
        for (int i = 0; i < ticketCount; i++) {
            ticketPool.add("Ticket");
            ticketWebSocketHandler.broadcastTicketCount(getAvailableTickets());
        }

        // Log success and return
        logger.info(ticketCount + " tickets added to the ticket pool.");
        return true;
    }

    /**
     * Removes tickets from the pool. Ensures that the pool has enough tickets to remove.
     *
     * @param ticketCount The number of tickets to remove.
     * @return true if tickets were successfully removed, false otherwise.
     */
    public synchronized boolean removeTicket(int ticketCount) {

        // Check if the pool has enough tickets to remove
        if (ticketPool.size() < ticketCount || ticketPool.isEmpty()) {
            logger.warning("Ticket pool doesn't contain " + ticketCount + " tickets to remove.");
            return false;
        }

        // Removing the tickets from the pool
        for (int i = 0; i < ticketCount; i++) {
            ticketPool.remove("Ticket");
            ticketWebSocketHandler.broadcastTicketCount(getAvailableTickets());

        }
        // Log success and return
        logger.info("Ticket removed from the pool: " + ticketCount);
        return true;


    }

    /**
     * Retrieves the number of available tickets in the pool.
     *
     * @return the current number of available tickets.
     */
    public int getAvailableTickets() {
        return ticketPool.size();
    }




}
