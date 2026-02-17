package com.example.geographical.service;

import com.example.geographical.dto.request.GeographicalDataRequestDTO;
import com.example.geographical.dto.request.GetGeographicalDataByUserRequestDTO;
import com.example.geographical.dto.request.UpdateGeographicalDataRequestDTO;
import com.example.geographical.dto.response.GeographicalDataCreateResponseDTO;
import com.example.geographical.dto.response.GeographicalDataResponseDTO;
import com.example.geographical.response.ResponseModel;

import java.util.List;

public interface GeographicalService {

    GeographicalDataCreateResponseDTO addGeographicalData(
            GeographicalDataRequestDTO geographicalDataRequestDTO
    );


    ResponseModel getGeographicalDataByUser(GetGeographicalDataByUserRequestDTO requestDTO);

    List<GeographicalDataResponseDTO> getAllGeographicalData();

    void updateGeographicalData(UpdateGeographicalDataRequestDTO requestDTO);

    ResponseModel deleteGeographicalData(Long id);

    long getTotalGeographicalCount();



}
