package com.example.pikkonsultacje;

import com.example.pikkonsultacje.Entity.Consultation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConsultationControllerTests extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testGetUserConsultations() throws Exception {
        String username = "test_user1";
        String uri = "/consultation/" + username;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Consultation[] consultations = super.mapFromJson(content, Consultation[].class);

        for (Consultation consultation: consultations) {
            assertEquals(username, consultation.getStudent().getUsername());
        }
//        assertTrue(consultations.length > 0);
    }
}
