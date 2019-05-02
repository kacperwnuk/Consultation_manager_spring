package com.example.pikkonsultacje.Service.Security;

import com.example.pikkonsultacje.Entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class UserToUserDetailsConverter implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();

        if (user != null){
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getPassword());
            userDetails.setEnabled(user.getEnabled());
            userDetails.setRole(user.getRole());
        }
        return userDetails;
    }
}
