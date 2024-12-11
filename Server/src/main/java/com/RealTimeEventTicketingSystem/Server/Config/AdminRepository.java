package com.RealTimeEventTicketingSystem.Server.Config;

import com.RealTimeEventTicketingSystem.Server.Model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for interacting with the admins collection in MongoDB.
 * Extends MongoRepository to provide CRUD operations on User documents.
 */
public interface AdminRepository extends MongoRepository<Admin,String> {
    Admin findByUsername(String username);
}
