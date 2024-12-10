package com.RealTimeEventTicketingSystem.Server.Service;

import com.RealTimeEventTicketingSystem.Server.Model.TicketPool;
import com.RealTimeEventTicketingSystem.Server.Model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class VendorService {

    private final TicketPool ticketPool;
    private final ConfigService configService;
    private final ConcurrentHashMap<String, Vendor> activeVendors = new ConcurrentHashMap<>();


    @Autowired
    public VendorService(TicketPool ticketPool, ConfigService configService) {
        this.ticketPool = ticketPool;
        this.configService = configService;
    }

    public void releaseTickets(String vendorId, int ticketCount) {
        int ticketReleaseRate = configService.getConfig().getTicketReleaseRate();
        Vendor vendor = new Vendor(vendorId, ticketPool, ticketReleaseRate, ticketCount );
        activeVendors.put(vendorId, vendor);

        new Thread(vendor).start();
    }

    public void stopReleaseTickets(String vendorId) {
        Vendor vendor = activeVendors.get(vendorId);
        if (vendor != null) {
            vendor.stop();
            activeVendors.remove(vendorId);
        }
    }

}

