package com.example.pikkonsultacje.Service.Security;

import com.example.pikkonsultacje.Dao.UserRepository;
import com.example.pikkonsultacje.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository dao;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository dao){
        this.dao = dao;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> optionalUser = dao.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                dao.save(user);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
