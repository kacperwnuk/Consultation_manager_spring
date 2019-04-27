package com.example.pikkonsultacje.Controller;

import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import com.example.pikkonsultacje.Service.RegisterAndLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class RegisterAndLoginController {

    private RegisterAndLoginService service;

    @Autowired
    RegisterAndLoginController(RegisterAndLoginService registerAndLoginService) {
        this.service = registerAndLoginService;
    }

    /***
     * If login and passwd ok then sends user role.
     * @param principal - current user
     * @return User role.
     */
    @GetMapping("/login")
    public ResponseEntity<String> login(Principal principal) {

        Role userRole;
        try {
            userRole = service.getUserRole(principal.getName());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(userRole.toString(), HttpStatus.OK);
    }

    /***
     * Adding new user to database
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user) {

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        try {
            service.registerUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }


    //test
    @GetMapping("/register")
    public ResponseEntity<User> showUser() {
        return new ResponseEntity<>(new User("1", "kacper", new BCryptPasswordEncoder().encode("wnuk"), true, Role.STUDENT), HttpStatus.OK);
    }

    //test
    @RequestMapping("/index")
    public String getIndex() {
        return "Index";
    }


}
