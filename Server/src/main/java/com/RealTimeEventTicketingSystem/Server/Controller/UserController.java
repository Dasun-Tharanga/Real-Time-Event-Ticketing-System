package com.RealTimeEventTicketingSystem.Server.Controller;

import com.RealTimeEventTicketingSystem.Server.Config.TicketWebSocketHandler;
import com.RealTimeEventTicketingSystem.Server.Config.UserRepository;
import com.RealTimeEventTicketingSystem.Server.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authentication")
public class UserController {

    Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public void register(@RequestBody User user) {
         userRepository.save(user);
         logger.info("User registered: " + user.toString());
    }
    // need to make sure that the same username can't be added twice

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return "Login successful!";

        }else{
            return "Invalid username or password!";
        }
    }


}
