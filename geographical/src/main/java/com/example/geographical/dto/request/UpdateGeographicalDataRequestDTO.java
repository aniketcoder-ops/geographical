package com.example.geographical.dto.request;

import lombok.Data;

@Data
public class UpdateGeographicalDataRequestDTO {

    private Long gdId;
    private String userName;
    private Double latitude;
    private Double longitude;
    private String description;
    private String remarks;
}
