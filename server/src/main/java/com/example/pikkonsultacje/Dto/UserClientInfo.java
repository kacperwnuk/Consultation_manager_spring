package com.example.pikkonsultacje.Dto;

import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserClientInfo {

    private String name;
    private String surname;
    private String username;
    private Role role;
    private String phoneNumber;

    public UserClientInfo(User user) {
        this.username = user.getUsername();
        this.role = user.getRole();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phoneNumber = user.getPhoneNumber();
    }


    @Override
    public String toString() {
        return  "Imię: " + name + " Nazwisko: " + surname;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
