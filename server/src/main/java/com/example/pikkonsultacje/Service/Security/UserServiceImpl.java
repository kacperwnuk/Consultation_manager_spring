package com.example.pikkonsultacje.Service.Security;

import com.example.pikkonsultacje.Dao.UserRepository;
import com.example.pikkonsultacje.Dto.UserClientInfo;
import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository dao;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository dao){
        this.dao = dao;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> optionalUser = dao.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                dao.save(user);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean activateAccount(String studentUsername) {
        Optional<User> user = dao.findByUsername(studentUsername);
        if (user.isPresent()){
            User student = user.get();
            student.setEnabled(true);
            dao.save(student);
            return true;
        }
        return false;
    }

    @Override
    public List<UserClientInfo> getInactiveStudents() {
        Optional<List<User>> inactiveUsers = dao.findUsersByEnabledIsFalseAndRole(Role.STUDENT);
        return inactiveUsers.map(this::convertUsersToUserClientInfo).orElse(null);
    }

    @Override
    public boolean checkIfTutor(String tutorUsername) {
        Optional<User> user = dao.findByUsername(tutorUsername);
        return user.isPresent() && user.get().getRole() == Role.TUTOR;
    }

    @Override
    public List<UserClientInfo> getTutors() {
        Optional<List<User>> users = dao.findUsersByRole(Role.TUTOR);
        return users.map(this::convertUsersToUserClientInfo).orElse(null);
    }


    private List<UserClientInfo> convertUsersToUserClientInfo(List<User> users){
        List<UserClientInfo> userClientInfos = new LinkedList<>();
        for(User user : users){
            userClientInfos.add(new UserClientInfo(user));
        }
        return userClientInfos;
    }
}
