package com.example.pikkonsultacje.Dao;

import com.example.pikkonsultacje.Entity.Consultation;
import com.example.pikkonsultacje.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public
interface ConsultationRepository extends MongoRepository<Consultation, String> {

    List<Consultation> findByStudent(User student);
}
