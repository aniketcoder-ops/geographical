package com.example.geographical.controller;

import com.example.geographical.model.GeographicalData;
import com.example.geographical.repository.GeographicalRepository;
import com.example.geographical.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final GeographicalRepository geographicalRepository;

    @GetMapping("/dashboard")
    public String adminDashboard(
            Model model,
            @RequestParam(defaultValue = "0") int userPage,
            @RequestParam(defaultValue = "0") int dataPage
    ) {
        // Pagination for users - 10 per page
        Pageable userPageable = PageRequest.of(userPage, 10, Sort.by("id").descending());
        Page<?> usersPage = userRepository.findAll(userPageable);

        // Pagination for geographical data - 10 per page
        Pageable dataPageable = PageRequest.of(dataPage, 10, Sort.by("gdId").descending());
        Page<?> dataPageObj = geographicalRepository.findAll(dataPageable);

        // Add users pagination
        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("currentUserPage", userPage);
        model.addAttribute("totalUserPages", usersPage.getTotalPages());
        model.addAttribute("totalUsers", usersPage.getTotalElements());

        // Add geographical data pagination
        model.addAttribute("data", dataPageObj.getContent());
        model.addAttribute("currentDataPage", dataPage);
        model.addAttribute("totalDataPages", dataPageObj.getTotalPages());
        model.addAttribute("totalData", dataPageObj.getTotalElements());

        return "admin-dashboard";
    }

    // API endpoint to get single geographical data
    @GetMapping("/api/geo-data/{id}")
    @ResponseBody
    public ResponseEntity<?> getGeographicalData(@PathVariable Long id) {
        return geographicalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // API endpoint to update geographical data
    @PostMapping("/api/geo-data/update")
    @ResponseBody
    public ResponseEntity<?> updateGeographicalData(@RequestBody GeographicalData updatedData) {
        try {
            return geographicalRepository.findById(updatedData.getGdId())
                    .map(existingData -> {
                        existingData.setLatitude(updatedData.getLatitude());
                        existingData.setLongitude(updatedData.getLongitude());
                        existingData.setDescription(updatedData.getDescription());
                        existingData.setRemarks(updatedData.getRemarks());
                        existingData.setStatus(updatedData.getStatus());

                        GeographicalData saved = geographicalRepository.save(existingData);
                        return ResponseEntity.ok(Map.of("success", true, "message", "Data updated successfully"));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Update failed: " + e.getMessage()));
        }
    }

    // API endpoint to delete geographical data
    @DeleteMapping("/api/geo-data/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteGeographicalData(@PathVariable Long id) {
        try {
            if (geographicalRepository.existsById(id)) {
                geographicalRepository.deleteById(id);
                return ResponseEntity.ok(Map.of("success", true, "message", "Data deleted successfully"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Delete failed: " + e.getMessage()));
        }
    }
}