package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.MenuItemRequest;
import com.api.food_delivery_api.dto.MenuItemResponse;
import com.api.food_delivery_api.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public MenuItemResponse update(@PathVariable Long id,@RequestBody MenuItemRequest menuItemRequest)
    {
        return this.menuItemService.update(id, menuItemRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        this.menuItemService.delete(id);
    }

    @GetMapping("")
    public List<MenuItemResponse> getAll()
    {
        return this.menuItemService.getAll();
    }

    @GetMapping("/{id}")
    public MenuItemResponse getById(@PathVariable Long id)
    {
        return this.menuItemService.getById(id);
    }
}
