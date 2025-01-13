package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
