package com.fellowship;

import com.fellowship.controllers.AccountController;
import com.fellowship.database.ApplicationUser;
import com.fellowship.database.ApplicationUserRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testAccountController {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ApplicationUserRepository appUserRepo;

    @Autowired
    AccountController accountController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testUserLoads() throws Exception {
        ApplicationUser user = new ApplicationUser();
        user.setUsername("test");

        appUserRepo.save(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("test")));
    }

//    @Test
//    public void testUserCreation() {
//        String data = accountController.getLoginError();
//        assertEquals("Unrecognized user or password", data);
//
//        String result = accountController.signupFollowThrough(
//                "Big B",
//                "Real Money",
//                "Jeff",
//                "B",
//                "Own the world"
//        );
//
//        assertEquals("/login", result);
//
//        ApplicationUser user = appUserRepo.findByUsername("Big B");
//
//        assertEquals("Big B", user.getUsername());
//    }

}
