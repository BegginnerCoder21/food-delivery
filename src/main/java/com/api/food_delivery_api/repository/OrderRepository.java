package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
