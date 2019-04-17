package com.example.pikkonsultacje;

import com.example.pikkonsultacje.Controller.RegisterAndLoginController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PikKonsultacjeApplicationTests {

	@Autowired
    RegisterAndLoginController registerAndLoginController;

	@Test
	public void contextLoads() {
		Assert.assertEquals("Index", registerAndLoginController.getIndex());
	}

}
