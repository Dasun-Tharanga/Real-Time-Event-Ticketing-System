package com.RealTimeEventTicketingSystem.Server.Service;

import com.RealTimeEventTicketingSystem.Server.Model.Customer;
import com.RealTimeEventTicketingSystem.Server.Model.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class CustomerService {

    private final Logger logger = Logger.getLogger(CustomerService.class.getName());

    private final TicketPool ticketPool;
    private final ConfigService configService;

    @Autowired
    public CustomerService(TicketPool ticketPool, ConfigService configService) {
        this.ticketPool = ticketPool;
        this.configService = configService;
    }

    /**
     * Method to allow a customer to purchase tickets.
     * A new thread is created for each customer to simulate concurrent ticket purchasing.
     * @param customerID The unique identifier for the customer.
     * @param ticketCount The number of tickets the customer wants to purchase.
     */
    public void purchaseTickets(String customerID, int ticketCount) {

        try {
            // Load the customer retrieval rate from the configuration
            int customerRetrievalRate = configService.loadConfig().getCustomerRetrievalRate();

            // Ensure the retrieval rate is valid (positive)
            if (customerRetrievalRate <= 0) {
                logger.log(Level.SEVERE, "Invalid customer retrieval rate: " + customerRetrievalRate);
                throw new IllegalArgumentException("Invalid customer retrieval rate");
            }

            // Create a new customer object that simulates the purchase process
            Customer customer = new Customer(customerID, ticketCount, ticketPool, customerRetrievalRate);

            // Start the customer thread to simulate ticket purchasing
            new Thread(customer).start();
            logger.info("Customer " + customerID + " has started purchasing " + ticketCount + " tickets.");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while processing ticket purchase for customer " + customerID, e);
        }


    }
}
