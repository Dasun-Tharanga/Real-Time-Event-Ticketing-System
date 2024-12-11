package com.RealTimeEventTicketingSystem.Server.Controller;

import com.RealTimeEventTicketingSystem.Server.Config.AdminRepository;
import com.RealTimeEventTicketingSystem.Server.Config.TicketWebSocketHandler;
import com.RealTimeEventTicketingSystem.Server.Config.UserRepository;
import com.RealTimeEventTicketingSystem.Server.Model.Admin;
import com.RealTimeEventTicketingSystem.Server.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authentication")
public class UserController {

    Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    private UserRepository userRepository;

    @Autowired
    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Endpoint to register a new user in the system.
     * This method checks if the provided username already exists.
     * If it exists, the registration will be rejected with an appropriate message.
     * If the username is unique, the user is saved in the database, and a success message is returned.
     *
     * @param user the user object containing the registration details.
     * @return ResponseEntity containing a message and the appropriate HTTP status.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // Check if a user with the same username already exists in the database
        if (userRepository.findByUsername(user.getUsername()) != null) {
            // Log a warning message if the username already exists
            logger.warning("Registration failed, username already exists: " + user.getUsername());
            return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        }

        // Here, you would hash the password using a password encoder before saving it (e.g., BCrypt)
        // For now, assume password is being handled elsewhere (it's important to hash passwords).
        userRepository.save(user);
        logger.info("User registered successfully with username: " + user.getUsername());

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
    // need to make sure that the same username can't be added twice


    /**
     * Endpoint to log in an existing user by verifying their username and password.
     * The method checks if the username exists and if the password matches the stored one.
     * If both conditions are satisfied, the user is considered authenticated.
     *
     * @param username the username of the user attempting to log in.
     * @param password the password provided by the user for authentication.
     * @return ResponseEntity containing a message and HTTP status.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {

        // Attempt to find the user in the database by username
        User user = userRepository.findByUsername(username);

        // Check if the user exists and the provided password matches the stored password
        if (user != null && user.getPassword().equals(password)) {
            // Log successful login
            logger.info("User logged in successfully: " + username);
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            // Log failed login attempt (password mismatch or non-existent username)
            logger.warning("User login failed for username: " + username);
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }





}
