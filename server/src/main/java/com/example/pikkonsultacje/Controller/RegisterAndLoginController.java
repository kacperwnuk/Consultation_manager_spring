package com.example.pikkonsultacje.Controller;

import com.example.pikkonsultacje.Entity.Consultation;
import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import com.example.pikkonsultacje.Service.RegisterAndLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.time.LocalDateTime;


@RestController
public class RegisterAndLoginController {

    private RegisterAndLoginService service;

    RegisterAndLoginController(RegisterAndLoginService registerAndLoginService) {
        this.service = registerAndLoginService;
    }


    /***
     * If login and passwd ok then sends user role.
     * @param principal - current user
     * @return User role.
     */
    @GetMapping("/login")
    public ResponseEntity<String> login(Principal principal){

        Role userRole;
        try {
            userRole = service.getUserRole(principal.getName());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(userRole.toString(), HttpStatus.OK);
    }

    //test
    @GetMapping("/consultation")
    public ResponseEntity<Consultation> getCons(){
        return new ResponseEntity<>(new Consultation("1", new User("4", "tomek", new BCryptPasswordEncoder().encode("admin"), true, Role.STUDENT), new User("5", "Franek", new BCryptPasswordEncoder().encode("wnuk"), true, Role.STUDENT), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(2), "101"), HttpStatus.OK);
    }

    /***
     * Adding new consultation to db
     * @param consultation
     * @return
     */
    @PostMapping("/consultation")
    public ResponseEntity<String> addConsultation(@RequestBody Consultation consultation){
        service.addConsultation(consultation);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    /***
     * Adding new user to database
     * @param user
     * @return
     */
    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println("Wykonanie rejestracji uzytkownika:" + user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        /*try {
            service.registerUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }*/

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //test
    @GetMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> showUser() {
        System.out.println("Wykonanie wyslania");
        return new ResponseEntity<>(new User("1", "kacper123", new BCryptPasswordEncoder().encode("wnuk"), true, Role.STUDENT), HttpStatus.OK);
    }


    //test
    @RequestMapping("/index")
    public String getIndex() {
        return "Index";
    }


}
