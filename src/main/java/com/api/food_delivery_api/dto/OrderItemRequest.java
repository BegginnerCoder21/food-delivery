package com.api.food_delivery_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    private Integer quantity;
    private double price;
    private List<OrderMenuItemRequest> orderMenuItemRequests;
}
