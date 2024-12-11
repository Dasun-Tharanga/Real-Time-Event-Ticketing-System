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


    private AdminRepository adminRepository;

    public VendorController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * Endpoint to authenticate an admin user by verifying their username and password.
     * The method checks if the username exists in the database and if the provided
     * password matches the stored one. If both conditions are met, the user is authenticated.
     *
     * @param username the admin's username attempting to log in.
     * @param password the admin's password provided for authentication.
     * @return boolean indicating whether the login attempt was successful or not.
     */
    @PostMapping("/login")
    public boolean login(@RequestParam String username, @RequestParam String password) {
        // Log the username for debugging purposes
        logger.info("Attempting login for username: " + username);

        // Retrieve the admin from the database based on the provided username
        Admin admin = adminRepository.findByUsername(username);

        // Check if the admin exists and if the provided password matches the stored password
        if (admin != null && admin.getPassword().equals(password)) {
            // Log successful login
            logger.info("Admin logged in successfully: " + admin.toString());
            return true;
        } else {
            // Log failed login attempt
            logger.warning("Login failed for username: " + username);
            return false;
        }
    }
}
