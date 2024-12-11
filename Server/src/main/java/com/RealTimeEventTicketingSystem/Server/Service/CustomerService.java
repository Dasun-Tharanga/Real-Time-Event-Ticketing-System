package com.RealTimeEventTicketingSystem.Server.Service;

import com.RealTimeEventTicketingSystem.Server.Model.Customer;
import com.RealTimeEventTicketingSystem.Server.Model.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    private final TicketPool ticketPool;
    private final ConfigService configService;

    @Autowired
    public CustomerService(TicketPool ticketPool, ConfigService configService) {
        this.ticketPool = ticketPool;
        this.configService = configService;
    }

    public void purchaseTickets(String customerID, int ticketCount) {
        int customerRetrievalRate = configService.loadConfig().getCustomerRetrievalRate();

        Customer customer = new Customer(customerID, ticketCount ,ticketPool, customerRetrievalRate);

        new Thread(customer).start();


    }
}
