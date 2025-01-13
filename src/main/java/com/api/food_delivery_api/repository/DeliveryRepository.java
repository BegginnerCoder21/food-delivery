package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
