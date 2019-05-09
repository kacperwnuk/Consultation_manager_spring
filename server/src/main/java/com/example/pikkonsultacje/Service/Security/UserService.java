package com.example.pikkonsultacje.Service.Security;

import com.example.pikkonsultacje.Entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}
