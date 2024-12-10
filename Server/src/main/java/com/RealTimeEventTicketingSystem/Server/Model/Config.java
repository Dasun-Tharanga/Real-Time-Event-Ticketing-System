package com.RealTimeEventTicketingSystem.Server.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Configuration")
public class Config {

    @Id
    private String id;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int totalTickets;
    private int maximumTickets;

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getMaximumTickets() {
        return maximumTickets;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {

        this.ticketReleaseRate = ticketReleaseRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {

        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setTotalTickets(int totalTickets) {

        this.totalTickets = totalTickets;
    }

    public void setMaximumTickets(int maximumTickets) {

        this.maximumTickets = maximumTickets;
    }
}
