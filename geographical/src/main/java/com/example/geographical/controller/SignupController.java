package com.example.geographical.controller;

import com.example.geographical.model.User;
import com.example.geographical.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute User user) {

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign role based on email
        if ("admin@gmail.com".equalsIgnoreCase(user.getEmail())) {
            user.setRole("ROLE_ADMIN");
        } else {
            user.setRole("ROLE_USER");
        }

        userRepository.save(user);

        return "redirect:/login?signup=success";
    }
}