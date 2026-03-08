package com.example.geographical.controller;

import com.example.geographical.repository.GeographicalRepository;
import com.example.geographical.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final GeographicalRepository geographicalRepository;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("data", geographicalRepository.findAll());
        return "admin-dashboard";
    }
}