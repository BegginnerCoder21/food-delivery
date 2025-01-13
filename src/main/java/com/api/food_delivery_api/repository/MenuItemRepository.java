package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
