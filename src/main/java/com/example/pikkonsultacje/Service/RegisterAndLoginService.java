package com.example.pikkonsultacje.Service;


import com.example.pikkonsultacje.Dao.ConsultationRepository;
import com.example.pikkonsultacje.Dao.UserRepository;
import com.example.pikkonsultacje.Entity.Consultation;
import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import org.springframework.stereotype.Service;


@Service
public class RegisterAndLoginService {

    private UserRepository userRepository;
    private ConsultationRepository consultationRepository;

    public RegisterAndLoginService(UserRepository userRepository, ConsultationRepository consultationRepository) {
        this.userRepository = userRepository;
        this.consultationRepository = consultationRepository;
    }

    public void registerUser(User user) throws Exception {

        if (user.getUsername() == null || user.getPassword() == null) {
            throw new Exception("Username and password required!");
        }

        if (usernameAlreadyUsed(user.getUsername())) {
            throw new Exception("Username is already used!");
        }
        userRepository.insert(user);
    }

    private Boolean usernameAlreadyUsed(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public Role getUserRole(String login) throws Exception {
        User user = userRepository.findByUsername(login);
        Role role = user.getRole();
        if (role == null) {
            throw new Exception("There is no user with this login!");
        } else return role;
    }

    public void addConsultation(Consultation consultation) {
        consultationRepository.insert(consultation);
    }
}
