package com.example.pikkonsultacje.Service.Security;

import com.example.pikkonsultacje.Dao.Dao;
import com.example.pikkonsultacje.Entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private Dao dao;

    public UserServiceImpl(Dao dao){
        this.dao = dao;
    }

    @Override
    public User findByUsername(String username) {
        return dao.findUserByUsername(username);
    }
}
