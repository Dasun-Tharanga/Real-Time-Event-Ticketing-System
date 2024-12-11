package com.RealTimeEventTicketingSystem.Server.Controller;

import com.RealTimeEventTicketingSystem.Server.Config.AdminRepository;
import com.RealTimeEventTicketingSystem.Server.Config.TicketWebSocketHandler;
import com.RealTimeEventTicketingSystem.Server.Config.UserRepository;
import com.RealTimeEventTicketingSystem.Server.Model.Admin;
import com.RealTimeEventTicketingSystem.Server.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/adminAuthentication")
public class VendorController {

    Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    @Autowired
    private AdminRepository adminRepository;



    @PostMapping("/login")
    public boolean login(@RequestParam String username, @RequestParam String password) {
        System.out.println(username + " " + password);
        Admin admin = adminRepository.findByUsername(username);
        System.out.println(admin.toString());

        if (admin != null && admin.getPassword().equals(password)) {
            logger.info("User logged in: " + admin.toString());
            return true;


        }else{
            logger.info("User logged failed: " );
            return false;
        }
    }
}
