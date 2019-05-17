package com.example.pikkonsultacje.Service.Security;

import com.example.pikkonsultacje.Dto.UserClientInfo;
import com.example.pikkonsultacje.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    boolean changePassword(String username, String oldPassword, String newPassword);

    boolean activateAccount(String studentUsername);

    List<UserClientInfo> getInactiveStudents();

    boolean checkIfTutor(String tutorUsername);

    List<UserClientInfo> getTutors();
}
