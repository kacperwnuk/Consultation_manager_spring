package com.example.pikkonsultacje.Service;


import com.example.pikkonsultacje.Dao.Dao;
import com.example.pikkonsultacje.Entity.User;
import org.springframework.stereotype.Service;

@Service
public class RegisterAndLoginService {

    private Dao dao;

    public RegisterAndLoginService(Dao dao){
        this.dao = dao;
    }

    public void registerUser(User user) throws Exception{

        if (user.getUsername() == null || user.getPassword() == null){
            throw new Exception("Username and password required!");
        }

        if (usernameAlreadyUsed(user.getUsername())){
            throw new Exception("Username is already used!");
        }
        dao.addUser(user);
    }

    private Boolean usernameAlreadyUsed(String username){
        User user = dao.findUserByUsername(username);
        return user != null;
    }
}
