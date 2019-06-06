package com.example.pikkonsultacje.Controller;

import com.example.pikkonsultacje.Dao.ConsultationDao;
import com.example.pikkonsultacje.Dto.ConsultationSearchForm;
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
    @CrossOrigin(origins = "https://localhost:4200")
    @GetMapping("/consultation/{studentUsername}")
    public ResponseEntity<List<Consultation>> getUsersConsultations(@PathVariable String studentUsername) {
        List<Consultation> consultations = consultationDao.findStudentsConsultationsByHisUsername(studentUsername);
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }


    //test
    @GetMapping("/consultations")
    @CrossOrigin(origins = "https://localhost:4200")
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
    @CrossOrigin(origins = "https://localhost:4200")
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
    @CrossOrigin(origins = "https://localhost:4200")

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
    @GetMapping("/acceptConsultation")
    @CrossOrigin(origins = "https://localhost:4200")
    public ResponseEntity<Boolean> acceptConsultationCreatedByStudent(@RequestParam String consultationId, @RequestParam String username){
        boolean status = consultationService.acceptStudentConsultation(consultationId, username);
        if (status){
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("#username == authentication.name")
    @GetMapping("/reserveConsultation")
    @CrossOrigin(origins = "https://localhost:4200")
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
    @CrossOrigin(origins = "https://localhost:4200")
    public ResponseEntity<Boolean> cancelConsultation(@RequestParam String consultationId, @RequestParam String username) {
        boolean status = consultationService.cancelConsultation(consultationId, username);
        if (status) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/freeConsultations")
    @CrossOrigin(origins = "https://localhost:4200")
    public ResponseEntity<List<Consultation>> getFreeConsultations() {
        List<Consultation> consultations = consultationDao.findFreeConsultations();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @PreAuthorize("#username == authentication.name")
    @PostMapping("/searchConsultations")
    @CrossOrigin(origins = "https://localhost:4200")
    public ResponseEntity<List<Consultation>> getConsultationsUsingCriteria(@RequestBody ConsultationSearchForm consultationSearchForm, @RequestParam String username){
        List<Consultation> consultations = consultationService.findConsultations(consultationSearchForm);
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @PreAuthorize("#username == authentication.name")
    @PostMapping("/countConsultations")
    @CrossOrigin(origins = "https://localhost:4200")
    public ResponseEntity<Long> countConsultationsUsingCriteria(@RequestBody ConsultationSearchForm consultationSearchForm, @RequestParam String username){
        long consultationsCount = consultationService.countConsultations(consultationSearchForm);
        return new ResponseEntity<>(consultationsCount, HttpStatus.OK);
    }
}
