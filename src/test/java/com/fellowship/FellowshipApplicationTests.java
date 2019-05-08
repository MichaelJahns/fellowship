package com.fellowship;

import com.fellowship.database.ApplicationUser;
import com.fellowship.database.ApplicationUserRepository;
import com.fellowship.security.UserDetailsServiceImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FellowshipApplicationTests {

    @Autowired
    UserDetailsServiceImplementation userService;
    @Autowired
    ApplicationUserRepository appUserRepo;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testAccountCreation() {
        ApplicationUser Jeff = new ApplicationUser();
        Jeff.setUsername("Big B");
        Jeff.setPassword("jeff");
        Jeff.setFirstName("Jeff");
        Jeff.setLastInital("B");
        Jeff.setStatementOfPurpose("Own the world");

        assertEquals("Username unexpected", "Big B", Jeff.getUsername());
        assertEquals("Password unexpected", "jeff", Jeff.getPassword());
        assertEquals("State of Purpose unexpected", "Own the world", Jeff.getStatementOfPurpose());

        appUserRepo.save(Jeff);

        UserDetails query = userService.loadUserByUsername("Big B");
        assertEquals("Username unexpected", "Big B", query.getUsername());
    }

}
