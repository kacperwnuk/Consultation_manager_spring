package com.example.pikkonsultacje.Dao;

import com.example.pikkonsultacje.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
