package com.fellowship.controllers;

import com.fellowship.database.ApplicationUser;
import com.fellowship.database.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    @Autowired
    ApplicationUserRepository appUserRepo;

    @Autowired
    PasswordEncoder encoder;

    //Signup Routes
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupFollowThrough(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastInitial,
            @RequestParam String statementOfPurpose
    ) {
        ApplicationUser user = new ApplicationUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setFirstName(firstName);
        user.setLastInital(lastInitial);
        user.setStatementOfPurpose(statementOfPurpose);

        appUserRepo.save(user);

        //Auto login stretch goal
        return "/login";
    }
}
