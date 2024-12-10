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

    @Autowired
    private ConfigService configService;

//    @Autowired
//    public configController(ConfigService configService) {
//        this.configService = configService;
//    }

    //Retrieving configuration data from the client
    @PostMapping
    public ResponseEntity<Map<String, String>> saveConfig(@RequestBody Config config) {
        configService.saveConfig(config);

        Map<String, String> response = new HashMap<>();
        response.put("status", "config saved");
        return ResponseEntity.ok(response);
    }

}
