package com.api.food_delivery_api.exeption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseEntityDto {

    private String errorCode;
    private int statusCode;
    private String message;
    private String messageDescription;
    private LocalDateTime timestamp;
    private Object responseData;
}
