package com.example.pikkonsultacje;


import com.example.pikkonsultacje.Dao.UserRepository;
import com.example.pikkonsultacje.Entity.User;
import com.example.pikkonsultacje.Service.RegisterAndLoginService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class ServiceTests {

    private RegisterAndLoginService service;
    private User user;
    @MockBean
    UserRepository userRepository;

    @Before
    public void setUp(){
        service = new RegisterAndLoginService(userRepository);
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


}
