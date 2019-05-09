package com.example.pikkonsultacje.Dao;

import com.example.pikkonsultacje.Entity.Consultation;
import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ConsultationDao {

    private ConsultationRepository consultationRepository;
    private UserRepository userRepository;

    @Autowired
    public ConsultationDao(ConsultationRepository consultationRepository, UserRepository userRepository) {
        this.consultationRepository = consultationRepository;
        this.userRepository = userRepository;
    }

    public List<Consultation> findAllConsultations() {
        return consultationRepository.findAll();
    }

    public List<Consultation> findStudentsConsultationsByHisUsername(String username) {
        Optional<User> student = userRepository.findByUsername(username);
        if (student.isPresent()) {
            return consultationRepository.findByStudent(student.get());
        } else {
            return Collections.emptyList();
        }
    }

    public Optional<Consultation> findConsultationById(String id) {
        return consultationRepository.findById(id);
    }

    public void insertConsultation(Consultation consultation) {
        consultationRepository.insert(consultation);
    }

    public void updateConsultation(Consultation consultation) {
        consultationRepository.save(consultation);
    }

    public void deleteConsultation(Consultation consultation) {
        consultationRepository.delete(consultation);
    }

    public List<Consultation> findFreeConsultations() {
        return consultationRepository.findByStatus(Status.FREE);
    }
}
