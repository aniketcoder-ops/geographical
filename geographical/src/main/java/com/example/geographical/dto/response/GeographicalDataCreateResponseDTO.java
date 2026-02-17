package com.example.geographical.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeographicalDataCreateResponseDTO {

    private Long gdId;
    private String uuid;
    private String userName;
    private Double latitude;
    private Double longitude;
    private String description;
    private String remarks;
    private Integer status;
    private LocalDateTime createdAt;
}
