package com.RealTimeEventTicketingSystem.Server.Controller;

import com.RealTimeEventTicketingSystem.Server.Config.TicketWebSocketHandler;
import com.RealTimeEventTicketingSystem.Server.Service.CustomerService;
import com.RealTimeEventTicketingSystem.Server.Service.TicketService;
import com.RealTimeEventTicketingSystem.Server.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tickets")
public class TicketController {

    private static final Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    private final TicketService ticketService;
    private final VendorService vendorService;
    private final CustomerService customerService;

    @Autowired
    public TicketController(TicketService ticketService, VendorService vendorService, CustomerService customerService) {
        this.ticketService = ticketService;
        this.vendorService = vendorService;
        this.customerService = customerService;
    }

    /**
     * Endpoint to add tickets to the pool by a vendor.
     *
     * @param ticketCount the number of tickets to be added.
     * @param vendorID    the vendor's ID responsible for adding tickets.
     */
    @PostMapping("/add")
    public void addTicket(@RequestParam int ticketCount, @RequestParam String vendorID) {
        try {
            vendorService.releaseTickets(vendorID, ticketCount);
            logger.info("Vendor " + vendorID + " is releasing " + ticketCount + " tickets.");
        }catch (Exception e) {
            logger.severe("Error while adding tickets: " + e.getMessage());
        }
    }

    /**
     * Endpoint to purchase tickets from the ticket pool.
     *
     * @param customerID the customer requesting the ticket purchase.
     * @param ticketCount the number of tickets to be purchased.
     */
    @PostMapping("/purchase")
    public void purchaseTicket(@RequestParam String customerID, @RequestParam int ticketCount) {
        try {
            customerService.purchaseTickets(customerID, ticketCount);
            logger.info("Customer " + customerID + " is attempting to purchase " + ticketCount + " tickets.");
        } catch (Exception e) {
            logger.severe("Error while purchasing tickets: " + e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve the available number of tickets in the pool.
     *
     * @return the current number of available tickets.
     */
    @PostMapping("/available")
    public int tickets() {
        try {
            int availableTickets = ticketService.getAvailableTickets();
            logger.info("Retrieved available tickets count: " + availableTickets);
            return availableTickets;
        } catch (Exception e) {
            logger.severe("Error while retrieving available tickets: " + e.getMessage());
            return 0; // return 0 in case of an error
        }
    }

    /**
     * Endpoint to stop the release of tickets by a vendor.
     *
     * @param vendorId the ID of the vendor whose ticket release should be stopped.
     */
    @PostMapping("/stopReleasingTickets")
    public void stopReleasingTickets(@RequestParam String vendorId) {
        try {
            vendorService.stopReleaseTickets(vendorId);
            logger.info("Ticket release by vendor " + vendorId + " has been stopped.");
        } catch (Exception e) {
            logger.severe("Error while stopping ticket release: " + e.getMessage());
        }

    }


}
