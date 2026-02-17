package com.example.geographical.dto.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeographicalDataProjectionDTO {

    private String userName;
    private Double latitude;
    private Double longitude;
    private String description;
    private String remarks;
}
