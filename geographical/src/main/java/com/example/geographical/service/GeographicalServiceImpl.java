package com.example.geographical.service;

import com.example.geographical.dto.projection.GeographicalDataProjectionDTO;
import com.example.geographical.dto.request.GeographicalDataRequestDTO;
import com.example.geographical.dto.request.GetGeographicalDataByUserRequestDTO;
import com.example.geographical.dto.request.UpdateGeographicalDataRequestDTO;
import com.example.geographical.dto.response.GeographicalDataCreateResponseDTO;
import com.example.geographical.dto.response.GeographicalDataResponseDTO;
import com.example.geographical.model.GeographicalData;
import com.example.geographical.repository.GeographicalRepository;
import com.example.geographical.response.ResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeographicalServiceImpl implements GeographicalService {

    @Autowired
    private GeographicalRepository geographicalRepository;


    @Override
    @CacheEvict(value = {"geoAll", "geoByUser", "geoCount"}, allEntries = true)
    public GeographicalDataCreateResponseDTO addGeographicalData(
            GeographicalDataRequestDTO dto) {

        GeographicalData data = new GeographicalData()
                .setUuid(dto.getUuid())
                .setUserName(dto.getUserName())
                .setLatitude(dto.getLatitude())
                .setLongitude(dto.getLongitude())
                .setDescription(dto.getDescription())
                .setRemarks(dto.getRemarks());

        GeographicalData saved = geographicalRepository.save(data);

        return new GeographicalDataCreateResponseDTO(
                saved.getGdId(),
                saved.getUuid(),
                saved.getUserName(),
                saved.getLatitude(),
                saved.getLongitude(),
                saved.getDescription(),
                saved.getRemarks(),
                saved.getStatus(),
                saved.getCreatedAt()
        );
    }


    @Override
    @Cacheable(value = "geoByUser", key = "#requestDTO.uuid")
    public ResponseModel getGeographicalDataByUser(GetGeographicalDataByUserRequestDTO requestDTO) {

        List<GeographicalDataProjectionDTO> projectionList =
                geographicalRepository.findByUuid(requestDTO.getUuid());

        List<GeographicalDataResponseDTO> responseList = new ArrayList<>();

        for (GeographicalDataProjectionDTO dto : projectionList) {
            responseList.add(
                    new GeographicalDataResponseDTO(
                            dto.getUserName(),
                            dto.getLatitude(),
                            dto.getLongitude(),
                            dto.getDescription(),
                            dto.getRemarks()
                    )
            );
        }

        if (!responseList.isEmpty()) {
            return new ResponseModel(200, "Success", responseList);
        }

        return new ResponseModel(204, "Data Not Found", null);
    }


    @Override
    @Cacheable(value = "geoAll")
    public List<GeographicalDataResponseDTO> getAllGeographicalData() {

        List<GeographicalData> dataList =
                geographicalRepository.findAllByOrderByGdIdDesc();

        List<GeographicalDataResponseDTO> responseList = new ArrayList<>();

        for (GeographicalData data : dataList) {
            responseList.add(
                    new GeographicalDataResponseDTO(
                            data.getUserName(),
                            data.getLatitude(),
                            data.getLongitude(),
                            data.getDescription(),
                            data.getRemarks()
                    )
            );
        }

        return responseList;
    }


    @Override
    @CacheEvict(value = {"geoAll", "geoByUser", "geoCount"}, allEntries = true)
    public void updateGeographicalData(UpdateGeographicalDataRequestDTO requestDTO) {

        GeographicalData data = geographicalRepository
                .findById(requestDTO.getGdId())
                .orElseThrow(() -> new RuntimeException("Geographical data not found"));

        data.setUserName(requestDTO.getUserName());
        data.setLatitude(requestDTO.getLatitude());
        data.setLongitude(requestDTO.getLongitude());
        data.setDescription(requestDTO.getDescription());
        data.setRemarks(requestDTO.getRemarks());

        geographicalRepository.save(data);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"geoAll", "geoByUser", "geoCount"}, allEntries = true)
    public ResponseModel deleteGeographicalData(Long id) {

        int rows = geographicalRepository.softDeleteById(id);

        if (rows > 0) {
            return new ResponseModel(200, "Record deactivated successfully", null);
        }

        return new ResponseModel(404, "Record not found or already inactive", null);
    }


    @Override
    @Cacheable(value = "geoCount")
    public long getTotalGeographicalCount() {
        return geographicalRepository.count();
    }

}
