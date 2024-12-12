package com.RealTimeEventTicketingSystem.Server.Controller;

import com.RealTimeEventTicketingSystem.Server.Config.TicketWebSocketHandler;
import com.RealTimeEventTicketingSystem.Server.Model.Config;
import com.RealTimeEventTicketingSystem.Server.Service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/config")
public class configController {

    Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    private ConfigService configService;

    @Autowired
    public configController(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * Endpoint to save configuration data received from the client.
     *
     * @param config the configuration data to be saved.
     * @return a response containing a status message.
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> saveConfig(@RequestBody Config config) {
        try {
            // Save the configuration data
            configService.saveConfig(config);

            // Prepare the response message
            Map<String, String> response = new HashMap<>();
            response.put("status", "config saved");

            // Log successful saving
            logger.info("Configuration data saved successfully.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the error
            logger.severe("Error saving configuration: " + e.getMessage());

            // Prepare an error response
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Failed to save configuration");

            return ResponseEntity.status(500).body(response); // Return 500 for server error
        }
    }

}
