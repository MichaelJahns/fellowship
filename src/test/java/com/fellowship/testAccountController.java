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
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    public void loginIntegration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("login"))
                .andExpect(content().string(Matchers.containsString("Log in")));
    }

    @Test
    public void signupIntegration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/signup"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("signup"))
                .andExpect(content().string(Matchers.containsString("Submit")));
    }

    @Test
    public void testGuestRedirection() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/in/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("login"))
                .andExpect(content().string(Matchers.containsString("Log in")));
    }

    @Test
    public void landingIntegration() throws Exception {
        //How do i test that a User must be logged in
        //Principal?
        mockMvc.perform(MockMvcRequestBuilders.get("/in/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("feed"))
                .andExpect(content().string(Matchers.containsString("By")))
                .andExpect(content().string(Matchers.containsString("Post")));

    }

    @Test
    public void testUserCreation() {
        RedirectView result = accountController.signupFollowThrough(
                "The Mad Titan",
                "stones",
                "Thanos",
                "Perfect Balance"
        );

        RedirectView landing = new RedirectView("/in/");
        assertEquals("user was not redirected as expected", landing.toString(), result.toString());

        ApplicationUser user = appUserRepo.findByUsername("The Mad Titan");

        assertEquals("The Mad Titan", user.getUsername());
    }

}
