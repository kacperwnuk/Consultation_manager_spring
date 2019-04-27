package com.example.pikkonsultacje.Service;

import com.example.pikkonsultacje.Dao.ConsultationDao;
import com.example.pikkonsultacje.Dao.UserDao;
import com.example.pikkonsultacje.Entity.Consultation;
import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
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
            if (con.reserve(userDao.findUserByUsername(username))) {
                consultationDao.updateConsultation(con);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void addConsultation(Consultation consultation, String username) {
        consultation.setTutor(userDao.findUserByUsername(username));
        consultationDao.insertConsultation(consultation);
    }

    public boolean cancelConsultation(String consultationId, String username) {
        Optional<Consultation> consultation = consultationDao.findConsultationById(consultationId);
        if (consultation.isPresent()) {
            Consultation con = consultation.get();
            User user = userDao.findUserByUsername(username);
            if (user.getRole() == Role.STUDENT) {
                con.free();
                consultationDao.updateConsultation(con);
            } else if (user.getRole() == Role.TUTOR) {
                consultationDao.deleteConsultation(con);
            }
            return true;
        } else {
            return false;
        }
    }
}
