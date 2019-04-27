package com.example.pikkonsultacje.Controller;

import com.example.pikkonsultacje.Dao.ConsultationDao;
import com.example.pikkonsultacje.Entity.Consultation;
import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import com.example.pikkonsultacje.Service.RegisterAndLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ConsultationController {

    private RegisterAndLoginService service;

    private ConsultationDao consultationDao;

    @Autowired
    ConsultationController(RegisterAndLoginService registerAndLoginService,
                           ConsultationDao consultationDao) {
        this.service = registerAndLoginService;
        this.consultationDao = consultationDao;
    }

    @GetMapping("/consultation/{studentUsername}")
    public ResponseEntity<List<Consultation>> getUsersConsultations(@PathVariable String studentUsername) {
        List<Consultation> consultations = consultationDao.findStudentsConsultationsByHisUsername(studentUsername);
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }


    //test
    @GetMapping("/consultations")
    public ResponseEntity<List<Consultation>> getConsultations() {
        List<Consultation> consultations = consultationDao.findAllConsultations();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    //test
    @GetMapping("/consultation")
    public ResponseEntity<Consultation> getCons() {
        return new ResponseEntity<>(new Consultation("1", new User("4", "tomek", new BCryptPasswordEncoder().encode("admin"), true, Role.STUDENT), new User("5", "Franek", new BCryptPasswordEncoder().encode("wnuk"), true, Role.STUDENT), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(2), "101"), HttpStatus.OK);
    }

    /***
     * Adding new consultation to db
     * @param consultation
     * @return
     */
    @PostMapping("/consultation")
    public ResponseEntity<String> addConsultation(@RequestBody Consultation consultation) {
//        service.addConsultation(new Consultation("1", new User("4", "tomek", new BCryptPasswordEncoder().encode("admin"), true, Role.STUDENT), new User("5", "Franek", new BCryptPasswordEncoder().encode("wnuk"), true, Role.STUDENT), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(2), "101"));
//        service.addConsultation(new Consultation("2", new User("4", "tomek", new BCryptPasswordEncoder().encode("admin"), true, Role.STUDENT), new User("5", "Franek", new BCryptPasswordEncoder().encode("wnuk"), true, Role.STUDENT), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(2), "101"));
        service.addConsultation(consultation);
        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
