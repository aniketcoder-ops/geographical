package com.example.geographical.controller;

import com.example.geographical.dto.request.GeographicalDataRequestDTO;
import com.example.geographical.dto.request.GetGeographicalDataByUserRequestDTO;
import com.example.geographical.dto.request.UpdateGeographicalDataRequestDTO;
import com.example.geographical.dto.response.GeographicalDataResponseDTO;
import com.example.geographical.response.ResponseModel;
import com.example.geographical.service.GeographicalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geographical")
public class GeographicalController {

    private final GeographicalService geographicalService;

    public GeographicalController(GeographicalService geographicalService) {
        this.geographicalService = geographicalService;
    }


    @PostMapping("/add")
    public ResponseEntity<ResponseModel> addGeographicalData(
            @RequestBody GeographicalDataRequestDTO dto) {

        return ResponseEntity.status(201).body(
                new ResponseModel(
                        201,
                        "Geographical data created successfully",
                        geographicalService.addGeographicalData(dto)
                )
        );
    }


    @PostMapping("/getbyuser")
    public ResponseEntity<ResponseModel> getByUser(
            @RequestBody GetGeographicalDataByUserRequestDTO requestDTO) {

        return ResponseEntity.ok(
                geographicalService.getGeographicalDataByUser(requestDTO)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<GeographicalDataResponseDTO>> getAll() {
        return ResponseEntity.ok(
                geographicalService.getAllGeographicalData()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(
            @RequestBody UpdateGeographicalDataRequestDTO requestDTO) {

        geographicalService.updateGeographicalData(requestDTO);
        return ResponseEntity.ok("Updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {

        return ResponseEntity.ok(
                geographicalService.deleteGeographicalData(id)
        );
    }
}
