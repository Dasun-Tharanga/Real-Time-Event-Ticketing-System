package com.RealTimeEventTicketingSystem.Server.Controller;

import com.RealTimeEventTicketingSystem.Server.Service.CustomerService;
import com.RealTimeEventTicketingSystem.Server.Service.TicketService;
import com.RealTimeEventTicketingSystem.Server.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public void addTicket(@RequestParam int count, @RequestParam String vendorID) {
        vendorService.releaseTickets(vendorID, count);
    }

    @PostMapping("/purchase")
    public void purchaseTicket(@RequestParam String customerID, @RequestParam int ticketCount) {
        customerService.purchaseTickets(customerID, ticketCount);
    }

    @PostMapping("/available")
    public int tickets() {
        return ticketService.getAvailableTickets();
    }


}
