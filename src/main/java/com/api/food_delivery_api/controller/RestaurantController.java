package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.RestaurantRequest;
import com.api.food_delivery_api.dto.RestaurantResponse;
import com.api.food_delivery_api.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food-delivery/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("")
    public RestaurantResponse create(@RequestBody RestaurantRequest restaurantRequest)
    {
        return this.restaurantService.create(restaurantRequest);
    }
}
