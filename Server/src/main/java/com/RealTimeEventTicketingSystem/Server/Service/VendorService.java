package com.RealTimeEventTicketingSystem.Server.Service;

import com.RealTimeEventTicketingSystem.Server.Config.TicketWebSocketHandler;
import com.RealTimeEventTicketingSystem.Server.Model.TicketPool;
import com.RealTimeEventTicketingSystem.Server.Model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class VendorService {

    private static final Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    private final TicketPool ticketPool;
    private final ConfigService configService;
    // A HashMap is a data structure that stores key-value pairs, where each key is unique and maps to a specific value.
    // A ConcurrentHashMap is a thread-safe version of a HashMap.
    private final ConcurrentHashMap<String, Vendor> activeVendors = new ConcurrentHashMap<>();


    @Autowired
    public VendorService(TicketPool ticketPool, ConfigService configService) {
        this.ticketPool = ticketPool;
        this.configService = configService;
    }

    /**
     * Releases tickets from the vendor to the pool.
     * @param vendorId The ID of the vendor releasing tickets.
     * @param ticketCount The number of tickets the vendor intends to release.
     */
    public void releaseTickets(String vendorId, int ticketCount) {
        try {
            // Retrieve ticket release rate from configuration service
            int ticketReleaseRate = configService.loadConfig().getTicketReleaseRate();
            // Create a new vendor instance and add to active vendors map
            Vendor vendor = new Vendor(vendorId, ticketPool, ticketReleaseRate, ticketCount);
            activeVendors.put(vendorId, vendor);

            // Start the vendor's ticket release process in a separate thread
            new Thread(vendor).start();
            logger.info("Vendor " + vendorId + " started releasing tickets to the pool.");

        } catch (Exception e) {
            // Log any unexpected errors during the ticket release process
            logger.log(Level.SEVERE, "Error occurred while releasing tickets for vendor " + vendorId, e);
        }
    }

    /**
     * Stops the ticket release process for a specific vendor.
     * @param vendorId The ID of the vendor whose ticket release should be stopped.
     */
    public void stopReleaseTickets(String vendorId) {
        try {
            Vendor vendor = activeVendors.get(vendorId);

            if (vendor != null) {
                // Stop the vendor's ticket release process
                vendor.stop();
                activeVendors.remove(vendorId);
                logger.info("Vendor " + vendorId + " has stopped releasing tickets.");
            } else {
                // If vendor is not found, log the warning
                logger.warning("Vendor " + vendorId + " not found in active vendors.");
            }
        } catch (Exception e) {
            // Log any errors while attempting to stop the ticket release process
            logger.log(Level.SEVERE, "Error occurred while stopping ticket release for vendor " + vendorId, e);
        }
    }

}

