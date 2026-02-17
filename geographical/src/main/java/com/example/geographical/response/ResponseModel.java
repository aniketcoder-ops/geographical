package com.example.geographical.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResponseModel {

    private int status;       // HTTP status code, e.g., 200, 204
    private String message;   // Success/Error message
    private Object data;      // Can be List, DTO, or null
}
