package com.api.food_delivery_api.dto;

import com.api.food_delivery_api.entity.Restaurant;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequest {

    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer availability;
    private Long restaurantId;
}
