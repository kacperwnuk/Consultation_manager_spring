package com.example.pikkonsultacje.Service.Security;

import com.example.pikkonsultacje.Dao.UserRepository;
import com.example.pikkonsultacje.Entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository dao;

    public UserServiceImpl(UserRepository dao){
        this.dao = dao;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return dao.findByUsername(username);
    }
}
