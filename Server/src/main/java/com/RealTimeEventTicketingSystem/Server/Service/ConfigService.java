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

    Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    @Autowired
    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    //Method to get configuration data from MongoDB
    public Config getConfig(){

        Optional<Config> configOptional = configRepository.findById("674b5bbd32eb257e8e1d45d4");

        if (configOptional.isPresent()) {
            return configOptional.get();
        } else {
            throw new RuntimeException("Ticket configuration not found");
        }
    }

    //Saving configuration data to JSON file
    public void saveConfig(Config config){
        //matches the formData object from the client to the config object in the server
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            objectMapper.writeValue(new File("../Files/configuration.json"), config);
            logger.info("Ticket configuration saved to the JSON file");
        }catch(IOException e){
            throw new RuntimeException("Error writing config to JSON file", e);

        }
    }

    // Reading configuration dara from JSON file
    public Config loadConfig(){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            Config config = objectMapper.readValue(new File("../Files/configuration.json"), Config.class);
            logger.info("Ticket configuration loaded from the JSON file");
            return config;

        } catch (IOException e) {
            throw new RuntimeException("Error writing config to JSON file", e);
        }
    }
}
