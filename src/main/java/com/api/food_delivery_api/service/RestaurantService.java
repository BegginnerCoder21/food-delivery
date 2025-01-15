package com.api.food_delivery_api.service;


import com.api.food_delivery_api.dto.RestaurantRequest;
import com.api.food_delivery_api.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    RestaurantResponse create(RestaurantRequest deliveryPartnerRequest);
    RestaurantResponse update(Long id, RestaurantRequest deliveryPartnerRequest);
    void delete(Long id);
    List<RestaurantResponse> getAll();
    RestaurantResponse getById(Long id);
}
