package com.api.food_delivery_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {

    private String code;
    private String name;
    private String category;
    private double rating;
    private String phoneNumber;
    private String location;
    private String logoUrl;
    private Date openTime;
    private Date closeTime;
    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;
}
