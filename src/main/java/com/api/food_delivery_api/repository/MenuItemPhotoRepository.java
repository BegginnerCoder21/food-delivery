package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.MenuItemPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemPhotoRepository extends JpaRepository<MenuItemPhoto, Long> {
}
