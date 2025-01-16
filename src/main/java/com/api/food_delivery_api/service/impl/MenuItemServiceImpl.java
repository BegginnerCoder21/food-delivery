package com.api.food_delivery_api.service.impl;

import com.api.food_delivery_api.dto.MenuItemRequest;
import com.api.food_delivery_api.dto.MenuItemResponse;
import com.api.food_delivery_api.entity.MenuItem;
import com.api.food_delivery_api.entity.Restaurant;
import com.api.food_delivery_api.repository.MenuItemRepository;
import com.api.food_delivery_api.repository.RestaurantRepository;
import com.api.food_delivery_api.service.MenuItemService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MenuItemResponse create(MenuItemRequest menuItemRequest) {

        boolean restaurantExist = this.restaurantRepository.existsById(menuItemRequest.getRestaurantId());

        if(!restaurantExist)
        {
            log.info("Aucun restaurant avec l'id {} n'a été trouvé", menuItemRequest.getRestaurantId());
            return MenuItemResponse.builder().build();
        }

        Restaurant foundRestaurant = this.restaurantRepository.findById(menuItemRequest.getRestaurantId()).get();

        MenuItem menuItemCreating = MenuItem.builder()
                .price(menuItemRequest.getPrice())
                .availability(menuItemRequest.getAvailability())
                .code(menuItemRequest.getCode())
                .description(menuItemRequest.getDescription())
                .name(menuItemRequest.getName())
                .restaurant(foundRestaurant)
                .build();
        menuItemCreating.setCreatedAt(new Date());
        menuItemCreating.setCreatedBy("SYSTEM");

        this.menuItemRepository.save(menuItemCreating);

        log.info("Enregistrement de {} effectué avec succès.", menuItemCreating);

        return this.modelMapper.map(menuItemCreating, MenuItemResponse.class);
    }

    @Override
    public MenuItemResponse update(Long id, MenuItemRequest menuItemRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<MenuItemResponse> getAll() {
        return List.of();
    }

    @Override
    public MenuItemResponse getById(Long id) {
        return null;
    }
}
