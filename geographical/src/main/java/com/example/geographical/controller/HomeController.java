package com.example.geographical.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Model model, Authentication authentication) {

        boolean loggedIn = authentication != null
                && authentication.isAuthenticated()
                && !(authentication.getPrincipal().equals("anonymousUser"));

        boolean isAdmin = false;

        if (loggedIn) {
            isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        }

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("isAdmin", isAdmin);

        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Authentication authentication) {
        // If user is already authenticated, redirect to appropriate dashboard
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal().equals("anonymousUser"))) {

            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/user/dashboard";
            }
        }

        return "login";
    }
}