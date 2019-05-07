package com.fellowship;

import com.fellowship.database.ApplicationUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testAccountController {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ApplicationUserRepository appUserRepo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webapplicationContext).build();
    }

    @Test
    public void testMVCthings() {

    }

}
