package com.api.food_delivery_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuItemRequest {

    private String code;
    private String name;
    private BigDecimal price;
}
