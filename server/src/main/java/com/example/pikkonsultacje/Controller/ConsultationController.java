package com.example.pikkonsultacje.Controller;

import com.example.pikkonsultacje.Dao.ConsultationDao;
import com.example.pikkonsultacje.Enum.Status;
import com.example.pikkonsultacje.Service.ConsultationService;
import com.example.pikkonsultacje.Entity.Consultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsultationController {

    private ConsultationDao consultationDao;

    private ConsultationService consultationService;

    @Autowired
    ConsultationController(ConsultationDao consultationDao, ConsultationService consultationService) {
        this.consultationDao = consultationDao;
        this.consultationService = consultationService;
    }

    @PreAuthorize("#studentUsername == authentication.name")
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

    /***
     * Adding new consultation to db
     * @param consultation
     * @return
     */
    @PostMapping("/consultation")
    public ResponseEntity<Boolean> addConsultationCreatedByTutor(@RequestBody Consultation consultation, @RequestParam String tutorUsername) {
        consultation.setStatus(Status.FREE);
        boolean status = consultationService.addConsultation(consultation, tutorUsername);
        if (status) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }


    /***
     * Adding new consultation to db
     * @param consultation
     * @return
     */
    @PostMapping("/studentConsultation")
    public ResponseEntity<Boolean> addConsultationCreatedByStudent(@RequestBody Consultation consultation, @RequestParam String studentUsername, @RequestParam String tutorUsername) {
        consultation.setStatus(Status.FREE);
        boolean status = consultationService.addConsultationCreatedByStudent(consultation, studentUsername, tutorUsername);
        if (status) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }



    @PreAuthorize("#username == authentication.name")
    @GetMapping("/reserveConsultation")
    public ResponseEntity<Boolean> reserveConsultation(@RequestParam String consultationId, @RequestParam String username) {
        boolean status = consultationService.reserveConsultation(consultationId, username);
        if (status) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/cancelConsultation")
    public ResponseEntity<Boolean> cancelConsultation(@RequestParam String consultationId, @RequestParam String username) {
        boolean status = consultationService.cancelConsultation(consultationId, username);
        if (status) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/freeConsultations")
    public ResponseEntity<List<Consultation>> getFreeConsultations() {
        List<Consultation> consultations = consultationDao.findFreeConsultations();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }
}
