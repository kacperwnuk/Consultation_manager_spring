package com.example.pikkonsultacje.Service;

import com.example.pikkonsultacje.Dao.UserRepository;
import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import org.springframework.stereotype.Service;

@Service
public class RegisterAndLoginService {

    private UserRepository userRepository;

    public RegisterAndLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(User user) {

        if (user.getUsername() == null || user.getPassword() == null) {
            System.out.println("Brak username lub hasla");
            return false;
        }

        if (!user.getUsername().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
            System.out.println("Zły mail");
            return false;
        }

        if (usernameAlreadyUsed(user.getUsername())) {
            System.out.println("Taki użytkownik istnieje!");
            return false;
        }
        userRepository.insert(user);
        return true;
    }

    private Boolean usernameAlreadyUsed(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Role getUserRole(String login) throws Exception {
        User user = userRepository.findByUsername(login).get();
        Role role = user.getRole();
        if (role == null) {
            throw new Exception("There is no user with this login!");
        } else return role;
    }
}
