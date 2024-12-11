package CLI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class Configuration {

    private int totalTickets = 0;
    private int ticketReleaseRate = 0;
    private int customerRetrievalRate = 0;
    private int maximumTickets = 0;

    public  Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maximumTickets) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maximumTickets = maximumTickets;

    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maximumTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setMaxTicketCapacity(int maximumTickets) {
        this.maximumTickets = maximumTickets;
    }

    public void saveConfiguration(String filePath){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter writer = new FileWriter(filePath)){
            gson.toJson(this, writer);
            System.out.println("Saved configuration to:  " + filePath);
        }catch (IOException e){
            System.out.println("Error saving configuration to " + e.getMessage());
        }

    }

}
