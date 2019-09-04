package com.example.pikkonsultacje.Service.Security;

import com.example.pikkonsultacje.Entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/***
 * Provides user information.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;
    private Converter<User, UserDetails> userToUserDetailsConverter;

    public UserDetailsServiceImpl(UserService userService, Converter<User, UserDetails> userUserDetailsConverter){
        this.userService = userService;
        this.userToUserDetailsConverter = userUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userToUserDetailsConverter.convert(userService.findByUsername(username).get());
    }
}
