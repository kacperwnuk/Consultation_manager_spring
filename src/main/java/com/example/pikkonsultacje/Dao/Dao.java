package com.example.pikkonsultacje.Dao;

import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/***
 *  Dao finds data in database.
 */
@Component
public class Dao {

    private List<User> users = new ArrayList<User>() {{
        add(new User("1", "kacper", new BCryptPasswordEncoder().encode("wnuk"), true, Role.STUDENT));
        add(new User("2", "admin", new BCryptPasswordEncoder().encode("admin"), false, Role.TUTOR));
    }};

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
