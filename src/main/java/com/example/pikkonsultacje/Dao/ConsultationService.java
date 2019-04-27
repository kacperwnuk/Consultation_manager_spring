package com.example.pikkonsultacje.Dao;

import com.example.pikkonsultacje.Entity.Consultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultationService {

    private ConsultationDao consultationDao;
    private UserDao userDao;

    @Autowired
    ConsultationService(ConsultationDao consultationDao, UserDao userDao) {
        this.consultationDao = consultationDao;
        this.userDao = userDao;
    }

    public boolean reserveConsultation(String consultationId, String username) {
        Optional<Consultation> consultation = consultationDao.findConsultationById(consultationId);
        if (consultation.isPresent()) {
            Consultation con = consultation.get();
            if (con.getStudent() != null) {
                con.setStudent(userDao.findUserByUsername(username));
                consultationDao.insert(con);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
