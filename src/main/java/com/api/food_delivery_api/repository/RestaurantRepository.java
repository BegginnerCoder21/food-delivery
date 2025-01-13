package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
