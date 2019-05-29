package com.example.pikkonsultacje.Controller;

import com.example.pikkonsultacje.Dto.ChangePasswordForm;
import com.example.pikkonsultacje.Dto.UserClientInfo;
import com.example.pikkonsultacje.Entity.Consultation;
import com.example.pikkonsultacje.Service.Security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.LinkedList;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("#userUsername == authentication.name")
    @PostMapping("/user/changePassword/{userUsername}")
    @CrossOrigin(origins = "https://localhost:4200")
    public ResponseEntity<String> changePassword(@PathVariable String userUsername, @RequestBody ChangePasswordForm changePasswordForm) {
        System.out.println("jestem tutaj w zmianei hasla");
        if (changePasswordForm.isCorrect()) {
            boolean status = userService.changePassword(userUsername, changePasswordForm.getOldPassword(), changePasswordForm.getNewPassword());
            if (status) {
                return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Couldn't change password", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Passwords don't match", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("#tutorUsername == authentication.name")
    @GetMapping("/user/activateStudent")
    @CrossOrigin(origins = "https://localhost:4200")
    public ResponseEntity<String> activateStudentAccount(@RequestParam String tutorUsername, @RequestParam String studentUsername) {
        boolean status = userService.activateAccount(studentUsername);
        if (status) {
            return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("#tutorUsername == authentication.name")
    @GetMapping("/user/inactiveStudents")
    @CrossOrigin(origins = "https://localhost:4200")
    public ResponseEntity<List<UserClientInfo>> showInactiveStudents(@RequestParam String tutorUsername) {
        boolean status = userService.checkIfTutor(tutorUsername);
        List<UserClientInfo> inactiveStudents = new LinkedList<>();
        if (status) {
             inactiveStudents = userService.getInactiveStudents();
        }
        return new ResponseEntity<>(inactiveStudents, HttpStatus.OK);
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/user/tutors")
    @CrossOrigin(origins = "https://localhost:4200")
    public ResponseEntity<List<UserClientInfo>> showTutors(@RequestParam String username) {
        List<UserClientInfo> tutors = userService.getTutors();
        return new ResponseEntity<>(tutors, HttpStatus.OK);
    }

}
