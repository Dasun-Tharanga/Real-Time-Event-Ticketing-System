package com.RealTimeEventTicketingSystem.Server.Config;

import com.RealTimeEventTicketingSystem.Server.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for interacting with the User collection in MongoDB.
 * Extends MongoRepository to provide CRUD operations on User documents.
 */
public interface UserRepository extends MongoRepository<User,String> {

    /**
     * Finds a user by their username.
     * This method is automatically implemented by Spring Data MongoDB based on the method signature.
     *
     * @param username The username of the user to search for.
     * @return The User object associated with the provided username, or null if no user is found.
     */
    User findByUsername(String username);
}
