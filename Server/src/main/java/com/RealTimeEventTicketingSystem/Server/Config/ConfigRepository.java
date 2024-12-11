package com.RealTimeEventTicketingSystem.Server.Config;

import com.RealTimeEventTicketingSystem.Server.Model.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for interacting with the Config collection in MongoDB.
 * Extends MongoRepository to provide CRUD operations on Config documents.
 */
public interface ConfigRepository extends MongoRepository<Config, String> {
}
