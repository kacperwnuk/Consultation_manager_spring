package com.example.pikkonsultacje.Controller;

import com.example.pikkonsultacje.Dto.ChangePasswordForm;
import com.example.pikkonsultacje.Entity.Consultation;
import com.example.pikkonsultacje.Service.Security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
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
    public ResponseEntity<String> changePassword(@PathVariable String userUsername, @RequestBody ChangePasswordForm changePasswordForm) {
        if (changePasswordForm.isCorrect()) {
            boolean status = userService.changePassword(userUsername,  changePasswordForm.getOldPassword(), changePasswordForm.getNewPassword());
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
    public ResponseEntity<String> activateStudentAccount(@RequestParam String tutorUsername, @RequestParam String studentUsername){
        boolean status = userService.activateAccount(studentUsername);
        if (status){
            return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }
}
