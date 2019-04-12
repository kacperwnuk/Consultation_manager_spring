package com.example.pikkonsultacje;


import com.example.pikkonsultacje.Dao.Dao;
import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Enum.Role;
import com.example.pikkonsultacje.Service.RegisterAndLoginService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ServiceTests {

    private RegisterAndLoginService service;
    private User user;

    @Before
    public void setUp(){
        Dao dao = new Dao();
        service = new RegisterAndLoginService(dao);
    }

    @Test
    public void throwExceptionWhenUserHasNoPasswd(){
        user = new User();

        try {
            service.registerUser(user);
            fail("Expected exception to be thrown");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Username and password required!"));
        }
    }

    @Test
    public void throwExceptionWhenUsernameIsAlreadyUsed(){
        user = new User("1", "id", "passwd", true, Role.STUDENT);
        try {
            service.registerUser(user);
            service.registerUser(user);
            fail("Expected exception to be thrown");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Username is already used!"));
        }


    }
}
