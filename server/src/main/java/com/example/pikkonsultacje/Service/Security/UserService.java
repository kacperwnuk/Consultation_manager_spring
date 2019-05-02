package com.example.pikkonsultacje.Service.Security;

import com.example.pikkonsultacje.Entity.User;

public interface UserService {
    User findByUsername(String username);
}
