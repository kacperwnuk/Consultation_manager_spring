package com.example.pikkonsultacje.Dao;

import com.example.pikkonsultacje.Entity.Consultation;
import com.example.pikkonsultacje.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        User student = userRepository.findByUsername(username);
        return consultationRepository.findByStudent(student);
    }

    public Optional<Consultation> findConsultationById(String id) {
        return consultationRepository.findById(id);
    }

    public void insert(Consultation consultation) {
        consultationRepository.insert(consultation);
    }
}
