package com.example.pikkonsultacje.Dto;


import lombok.Data;

@Data
public class ChangePasswordForm {

    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;

    public boolean isCorrect() {
        return newPassword.equals(newPasswordRepeat);
    }
}
