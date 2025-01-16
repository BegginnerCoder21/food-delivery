package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.MenuItemRequest;
import com.api.food_delivery_api.dto.MenuItemResponse;
import com.api.food_delivery_api.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food-delivery/menu-item")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("")
    public MenuItemResponse create(@RequestBody MenuItemRequest menuItemRequest)
    {
        return this.menuItemService.create(menuItemRequest);
    }
}
