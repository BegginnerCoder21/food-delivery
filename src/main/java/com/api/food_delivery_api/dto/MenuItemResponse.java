package com.api.food_delivery_api.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {

    private Long id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer availability;
    private Long restaurantId;
}
