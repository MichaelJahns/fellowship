package com.fellowship.controllers;

import com.fellowship.database.ApplicationUser;
import com.fellowship.database.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class AccountController {
    @Autowired
    ApplicationUserRepository appUserRepo;

    @Autowired
    PasswordEncoder encoder;

    //Signup Routes
    @GetMapping("/signup")
    public String signup(
            Principal p
    ) {
        //Im getting a null pointer exception, maybe a try catch?
        try {
            ApplicationUser user = appUserRepo.findByUsername(p.getName());
            if (user != null) {
                return "redirect:/in/";
            }
        } catch (Exception E) {
            System.out.println(E);
        }
        return "signup";

    }

    @PostMapping("/signup")
    public RedirectView signupFollowThrough(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String statementOfPurpose
    ) {
        ApplicationUser user = new ApplicationUser();
        user.setUsername(username);
        user.setPassword(this.encoder, password);
        user.setFirstName(firstName);
        user.setStatementOfPurpose(statementOfPurpose);

        // try Date dateJoined = new SimpleDateFormat(yyyy/MM/dd).parse(date)
        //user.setDateJoined(date)

        appUserRepo.save(user);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(token);

        return new RedirectView("/in/");
    }

    @GetMapping("/login")
    public String getLogin(
            @RequestParam(required = false) boolean errored,
            Principal p,
            Model model) {
        try {
            ApplicationUser user = appUserRepo.findByUsername(p.getName());
            if (user != null) {
                model.addAttribute("errored", true);
                return "redirect:/in/";
            }
        } catch (Exception E) {
            System.out.println(E);
        }
        return "login";
    }
}
