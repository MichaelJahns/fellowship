package com.fellowship.controllers;

import com.fellowship.database.ApplicationUser;
import com.fellowship.database.ApplicationUserRepository;
import com.fellowship.database.Post;
import com.fellowship.database.PostRepository;
import com.fellowship.security.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/in")
public class ViewController {

    @Autowired
    ApplicationUserRepository appUserRepo;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    public String getAllsPosts(
            Principal p,
            Model model) {
        try {
            ApplicationUser actor = appUserRepo.findByUsername(p.getName());
            List<Post> posts = this.postRepository.findAll();

            model.addAttribute("posts", posts);
            model.addAttribute("actor", actor);
            return "feed";
        } catch (Exception E) {
            return "login";
        }
    }

    @PostMapping("/")
    //Create a Post
    public RedirectView postMasterFollowThrough(
            @RequestParam String author,
            @RequestParam String body,
            Principal p
    ) {
        try {
            ApplicationUser actor = appUserRepo.findByUsername(p.getName());
            Post post = new Post();
            post.setBody(body);
            post.applicationUser = actor;

            postRepository.save(post);
            return new RedirectView("/in/profile");
        } catch (Exception E) {
            return new RedirectView("/login");
        }
    }

    //Go to your own account
    @GetMapping("/profile")
    public String getProfile(
            Principal p,
            Model model
    ) {
        try {
            ApplicationUser user = appUserRepo.findByUsername(p.getName());
            if (user != null) {
                return "redirect:/in/profile/" + user.getId();
            }
        } catch (Exception E) {
            System.out.println(E);
        }
        return "login";
    }

    @GetMapping("/profile/{id}")
    public String getUser(
            @PathVariable Long id,
            Principal p,
            Model model
    ) {
        Optional<ApplicationUser> foundUser = appUserRepo.findById(id);

        if (foundUser.isPresent()) {
            ApplicationUser actor = appUserRepo.findByUsername(p.getName());
            ApplicationUser user = foundUser.get();
            List<Post> posts = user.getPosts();

            model.addAttribute("actor", actor);
            model.addAttribute("user", user);
            model.addAttribute("posts", posts);

            return "profile";
        }
        throw new UserNotFoundException();
    }
}
