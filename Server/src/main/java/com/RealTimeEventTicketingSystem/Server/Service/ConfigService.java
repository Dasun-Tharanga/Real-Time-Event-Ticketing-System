package com.RealTimeEventTicketingSystem.Server.Service;

import com.RealTimeEventTicketingSystem.Server.ConfigRepository;
import com.RealTimeEventTicketingSystem.Server.Model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigService {

    private final ConfigRepository configRepository;

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
}
