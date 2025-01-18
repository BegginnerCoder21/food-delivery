package com.api.food_delivery_api.service;

import com.api.food_delivery_api.dto.OrderRequest;
import com.api.food_delivery_api.dto.OrderResponse;

public interface OrderService {

    OrderResponse create(OrderRequest orderRequest);
}
