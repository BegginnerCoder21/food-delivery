package com.api.food_delivery_api.utils;

import com.api.food_delivery_api.exeption.ApiResponseEntityDto;

import java.time.LocalDateTime;

public class ApiResponseUtils {


    public static ApiResponseEntityDto createApiResponseEntityDto(Object object, LocalDateTime timetamps, String messageDescription, String message,String errorCode, int statusCode )
    {

        return ApiResponseEntityDto.builder()
                .responseData(object)
                .timestamp(timetamps)
                .messageDescription(messageDescription)
                .message(message)
                .errorCode(errorCode)
                .statusCode(statusCode)
                .build();
    }

    public static ApiResponseEntityDto successResponse(Object object)
    {
        return ApiResponseUtils.createApiResponseEntityDto(
                object,
                LocalDateTime.now(),
                "Success",
                "Success",
                "200",
                200);
    }
}
