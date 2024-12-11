package com.RealTimeEventTicketingSystem.Server.Service;

import com.RealTimeEventTicketingSystem.Server.Config.ConfigRepository;
import com.RealTimeEventTicketingSystem.Server.Config.TicketWebSocketHandler;
import com.RealTimeEventTicketingSystem.Server.Model.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ConfigService {

    private final ConfigRepository configRepository;

    Logger logger = Logger.getLogger(ConfigService.class.getName());

    @Autowired
    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    //Method to get configuration data from MongoDB
    public Config getConfig(){

        // Fetch the configuration from MongoDB using a fixed ID
        Optional<Config> configOptional = configRepository.findById("674b5bbd32eb257e8e1d45d4");

        // If the configuration is found, return it, otherwise throw an exception
        if (configOptional.isPresent()) {
            return configOptional.get();
        } else {
            logger.severe("Ticket configuration not found in MongoDB.");
            throw new RuntimeException("Ticket configuration not found");
        }
    }

    // Method to save configuration data to JSON file
    public void saveConfig(Config config){
        //matches the formData object from the client to the config object in the server
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            // Serialize the Config object and save it to a file
            objectMapper.writeValue(new File("../Files/configuration.json"), config);
            logger.info("Ticket configuration saved to the JSON file");
        }catch(IOException e){
            logger.severe("Error writing config to JSON file: " + e.getMessage());
            throw new RuntimeException("Error writing config to JSON file", e);

        }
    }

    // Method to load configuration dara from JSON file
    public Config loadConfig(){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            // Read the JSON file and map it to a Config object
            Config config = objectMapper.readValue(new File("../Files/configuration.json"), Config.class);
            logger.info("Ticket configuration loaded from the JSON file");
            return config;

        } catch (IOException e) {
            logger.severe("Error reading config from JSON file: " + e.getMessage());
            throw new RuntimeException("Error reading config from JSON file", e);
        }
    }
}
