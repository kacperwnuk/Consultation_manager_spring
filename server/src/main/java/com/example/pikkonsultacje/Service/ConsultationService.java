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
            Optional<User> user = userDao.findUserByUsername(username);
            if (user.isPresent()) {
                if (con.reserve(user.get())) {
                    consultationDao.updateConsultation(con);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean addConsultation(Consultation consultation, String username) {
        Optional<User> user = userDao.findUserByUsername(username);
        if (user.isPresent()) {
            consultation.setTutor(user.get());
            consultationDao.insertConsultation(consultation);
            return true;
        }
        return false;
    }

    public boolean cancelConsultation(String consultationId, String username) {
        Optional<Consultation> consultation = consultationDao.findConsultationById(consultationId);
        if (consultation.isPresent()) {
            Consultation con = consultation.get();
            Optional<User> user = userDao.findUserByUsername(username);
            if (user.isPresent()) {
                if (user.get().getRole() == Role.STUDENT) {
                    con.free();
                    consultationDao.updateConsultation(con);
                } else if (user.get().getRole() == Role.TUTOR) {
                    consultationDao.deleteConsultation(con);
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
