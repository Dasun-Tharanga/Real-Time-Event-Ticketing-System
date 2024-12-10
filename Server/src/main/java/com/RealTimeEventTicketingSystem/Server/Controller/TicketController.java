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

    Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    @Autowired
    private TicketService ticketService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public void addTicket(@RequestParam int ticketCount, @RequestParam String vendorID) {
        vendorService.releaseTickets(vendorID, ticketCount);
    }

    @PostMapping("/purchase")
    public void purchaseTicket(@RequestParam String customerID, @RequestParam int ticketCount) {
        customerService.purchaseTickets(customerID, ticketCount);
    }

    @PostMapping("/available")
    public int tickets() {
        return ticketService.getAvailableTickets();
    }

    @PostMapping("/stopReleasingTickets")
    public void stopReleasingTickets(@RequestParam String vendorId) {
        vendorService.stopReleaseTickets(vendorId);
        logger.info("Ticket adding stopped.");

    }


}
