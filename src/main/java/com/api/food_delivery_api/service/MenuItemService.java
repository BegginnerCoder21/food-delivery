package com.api.food_delivery_api.service;

import com.api.food_delivery_api.dto.MenuItemRequest;
import com.api.food_delivery_api.dto.MenuItemResponse;

import java.util.List;

public interface MenuItemService {

    MenuItemResponse create(MenuItemRequest menuItemRequest);
    MenuItemResponse update(Long id, MenuItemRequest menuItemRequest);
    void delete(Long id);
    List<MenuItemResponse> getAll();
    MenuItemResponse getById(Long id);
}
