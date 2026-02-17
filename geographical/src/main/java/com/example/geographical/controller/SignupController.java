package com.example.geographical.controller;

import com.example.geographical.model.User;
import com.example.geographical.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String register(@ModelAttribute User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";
    }
}
