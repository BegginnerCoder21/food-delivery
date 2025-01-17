package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.MenuItemPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MenuItemPhotoRepository extends JpaRepository<MenuItemPhoto, Long> {

    public List<MenuItemPhoto> findAllByIdIn(Set<Long> id);
}
