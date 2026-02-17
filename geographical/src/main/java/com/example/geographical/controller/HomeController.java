package com.example.geographical.controller;

import com.example.geographical.service.GeographicalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final GeographicalService geographicalService;

    public HomeController(GeographicalService geographicalService) {
        this.geographicalService = geographicalService;
    }

    @GetMapping("/")
    public String home(Model model) {
        long totalCount = geographicalService.getTotalGeographicalCount();
        model.addAttribute("totalCount", totalCount);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // this should match login.html in templates
    }
}
