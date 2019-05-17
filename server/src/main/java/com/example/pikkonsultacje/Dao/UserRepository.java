package com.example.pikkonsultacje.Dao;

import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<List<User>> findUsersByEnabledIsFalseAndRole(Role role);
    Optional<List<User>> findUsersByRole(Role role);
}
