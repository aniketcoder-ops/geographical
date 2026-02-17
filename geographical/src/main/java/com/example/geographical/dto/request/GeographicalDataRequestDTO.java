package com.example.geographical.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class GeographicalDataRequestDTO {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("user_name")
    @JsonAlias("userName")
    private String userName;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("description")
    private String description;

    @JsonProperty("remarks")
    private String remarks;
}
