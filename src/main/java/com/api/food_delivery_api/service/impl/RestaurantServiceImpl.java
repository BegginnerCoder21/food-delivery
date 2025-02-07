package com.api.food_delivery_api.service.impl;

import com.api.food_delivery_api.dto.RestaurantRequest;
import com.api.food_delivery_api.dto.RestaurantResponse;
import com.api.food_delivery_api.entity.Restaurant;
import com.api.food_delivery_api.repository.RestaurantRepository;
import com.api.food_delivery_api.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RestaurantResponse create(RestaurantRequest deliveryPartnerRequest) {

        try {

            Restaurant restaurant = this.modelMapper.map(deliveryPartnerRequest, Restaurant.class);

            this.restaurantRepository.save(restaurant);

            return this.modelMapper.map(restaurant, RestaurantResponse.class);

        }catch (IllegalArgumentException el){
            throw new IllegalArgumentException("Mauvais remplissage du formulaire");

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public RestaurantResponse update(Long id, RestaurantRequest restaurantRequest) {

        try {

            boolean restaurantExist = this.restaurantRepository.existsById(id);

            if (!restaurantExist)
            {
                return RestaurantResponse.builder().build();
            }

            Restaurant restaurant = this.restaurantRepository.findById(id).get();

            this.modelMapper.map(restaurantRequest, restaurant);

            this.restaurantRepository.save(restaurant);

            return this.modelMapper.map(restaurant, RestaurantResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {

        boolean restaurantExist = this.restaurantRepository.existsById(id);

        if(!restaurantExist)
        {
            return;
        }

        this.restaurantRepository.deleteById(id);

    }

    @Override
    public List<RestaurantResponse> getAll() {

        List<Restaurant> restaurants = this.restaurantRepository.findAll();
        List<RestaurantResponse> restaurantResponsesList= new ArrayList<>();
        for (Restaurant restaurant: restaurants)
        {
            RestaurantResponse restaurantResponse = this.modelMapper.map(restaurant, RestaurantResponse.class);
            restaurantResponsesList.add(restaurantResponse);
        }
        return restaurantResponsesList;
    }

    @Override
    public RestaurantResponse getById(Long id) {

        boolean restaurantExist = this.restaurantRepository.existsById(id);

        if(!restaurantExist)
        {
            return RestaurantResponse.builder().build();
        }

        Restaurant restaurant = this.restaurantRepository.findById(id).get();

        return this.modelMapper.map(restaurant, RestaurantResponse.class);
    }
}
