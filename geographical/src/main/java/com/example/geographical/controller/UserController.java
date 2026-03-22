package com.example.geographical.controller;

import com.example.geographical.model.GeographicalData;
import com.example.geographical.repository.GeographicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final GeographicalRepository geographicalRepository;

    @GetMapping("/dashboard")
    public String userDashboard(
            Model model,
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page
    ) {
        String username = authentication.getName();

        // Pagination - 10 records per page
        Pageable pageable = PageRequest.of(page, 10, Sort.by("gdId").descending());
        Page<GeographicalData> dataPage = geographicalRepository.findByUserName(username, pageable);

        model.addAttribute("data", dataPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", dataPage.getTotalPages());
        model.addAttribute("totalElements", dataPage.getTotalElements());

        return "user-dashboard";
    }

    @GetMapping("/adddata")
    public String addDataPage(Model model) {
        model.addAttribute("geographicalData", new GeographicalData());
        return "add-data";
    }

    @PostMapping("/adddata")
    public String saveData(@ModelAttribute GeographicalData data,
                           Authentication authentication) {
        data.setUserName(authentication.getName());
        data.setUuid(UUID.randomUUID().toString());
        geographicalRepository.save(data);
        return "redirect:/user/dashboard";
    }
}