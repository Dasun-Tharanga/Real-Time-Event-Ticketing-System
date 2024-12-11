package com.RealTimeEventTicketingSystem.Server.Config;

import com.RealTimeEventTicketingSystem.Server.Model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,String> {
    Admin findByUsername(String username);
}
