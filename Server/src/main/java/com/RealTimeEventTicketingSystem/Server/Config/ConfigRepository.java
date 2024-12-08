package com.RealTimeEventTicketingSystem.Server.Config;

import com.RealTimeEventTicketingSystem.Server.Model.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<Config, String> {
}
