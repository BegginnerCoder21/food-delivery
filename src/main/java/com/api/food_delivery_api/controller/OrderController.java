package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.OrderRequest;
import com.api.food_delivery_api.service.OrderService;
import com.api.food_delivery_api.utils.ApiResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food-delivery/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody OrderRequest orderRequest)
    {

        return ResponseEntity.ok(ApiResponseUtils.successResponse(this.orderService.create(orderRequest)));

    }
}
