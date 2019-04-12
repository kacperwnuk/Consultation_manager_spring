package com.example.pikkonsultacje.Controller;

import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import com.example.pikkonsultacje.Service.RegisterAndLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
public class RegisterAndLoginController {

    private RegisterAndLoginService service;

    RegisterAndLoginController(RegisterAndLoginService registerAndLoginService) {
        this.service = registerAndLoginService;
    }

    @RequestMapping("/index")
    public String getIndex() {
        return "Index";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        try {
            service.registerUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

    @GetMapping("/register")
    public ResponseEntity<User> showUser() {
        return new ResponseEntity<>(new User("1", "kacper", new BCryptPasswordEncoder().encode("wnuk"), true, Role.STUDENT), HttpStatus.OK);
    }


}
