package com.example.geographical.controller;

import com.example.geographical.dto.request.GeographicalDataRequestDTO;
import com.example.geographical.service.GeographicalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/geographical")
public class GeographicalPageController {

    private final GeographicalService geographicalService;

    public GeographicalPageController(GeographicalService geographicalService) {
        this.geographicalService = geographicalService;
    }

    @GetMapping("/form")
    public String showForm() {
        return "geographical-form";
    }


    @PostMapping("/geographical/add")
    public String addGeographicalData(
            GeographicalDataRequestDTO dto,
            Model model
    ) {
        geographicalService.addGeographicalData(dto);
        model.addAttribute("successMessage", "Data saved successfully!");
        return "geographical-form";
    }

}
