package com.RealTimeEventTicketingSystem.Server.Service;

import com.RealTimeEventTicketingSystem.Server.Model.TicketPool;
import com.RealTimeEventTicketingSystem.Server.Model.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private TicketPool ticketPool;
    private List<Thread> vendors;
    private List<Thread> customers;

    public void TikcetService(){
        this.ticketPool = new TicketPool(100, 80);
        this.vendors = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void addVendor(int vendorID, int ticketReleaseRate){
        Vendor vendor = new Vendor(vendorID, ticketPool, ticketReleaseRate);
        Thread vendor = new Thread(new Vendor(vendorID, ticketPool, ticketReleaseRate));
        vendors.add(vendor);
        vendor.start();
    }

}
