package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.RestaurantRequest;
import com.api.food_delivery_api.dto.RestaurantResponse;
import com.api.food_delivery_api.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public RestaurantResponse update(@PathVariable Long id,@RequestBody RestaurantRequest restaurantRequest)
    {
        return this.restaurantService.update(id, restaurantRequest);
    }
}
