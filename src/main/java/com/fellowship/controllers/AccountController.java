package com.fellowship.controllers;

import com.fellowship.database.ApplicationUser;
import com.fellowship.database.ApplicationUserRepository;
import com.fellowship.security.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

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

        return new RedirectView("/feed");
    }

    @GetMapping("/feed")
    public String getUsers(Model model) {
        List<ApplicationUser> users = this.appUserRepo.findAll();
        model.addAttribute("users", users);

        return "feed";
    }

    @GetMapping("/user/{id}")
    public String getUser(
            @PathVariable Long id,
            Model model
    ) {
        Optional<ApplicationUser> foundUser = appUserRepo.findById(id);

        if (foundUser.isPresent()) {
            model.addAttribute("user", foundUser.get());
            return "user";
        }
        throw new UserNotFoundException();
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/login-error")
    @ResponseBody
    public String getLoginError() {
        return "Unrecognized user or password";
    }

}
