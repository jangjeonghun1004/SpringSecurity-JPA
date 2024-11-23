package com.example.demo.controller;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.service.AuthorityService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;
    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(
            UserService userService,
            AuthorityService authorityService,
            PasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    public String register() {
        return "register/register";
    }

    @PostMapping
    public String registerPost(
            @RequestParam String userid,
            @RequestParam String password
    ) {
        String authorityName = "USER";

        Optional<Authority> authority = this.authorityService.findByName(authorityName);
        Authority newAuthority = authority.orElseGet(
                () -> this.authorityService.save(Authority.builder().name(authorityName).build())
        );

        this.userService.save(User.builder()
                .userid(userid)
                .password(this.passwordEncoder.encode(password))
                .authorities(Set.of(newAuthority))
                .enabled(true).build()
        );

        return "/register/registerSuccess";
    }

}
