package com.api.food_delivery_api.service.handler.menuitemphoto;

import com.api.food_delivery_api.dto.MenuItemPhotoRequest;
import com.api.food_delivery_api.dto.MenuItemRequest;
import com.api.food_delivery_api.entity.MenuItemPhoto;
import com.api.food_delivery_api.repository.MenuItemPhotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuItemHandler {

    @Autowired
    private MenuItemPhotoRepository menuItemPhotoRepository;

    public boolean verifyMenuItemPhoto(MenuItemRequest menuItemRequest)
    {
        Set<Long> menuItemPhotoId = menuItemRequest.getMenuItemPhotoRequests().stream().map(MenuItemPhotoRequest::getId).collect(Collectors.toSet());
        List<MenuItemPhoto> menuItemPhotos = this.menuItemPhotoRepository.findAllByIdIn(menuItemPhotoId);

        if(menuItemPhotos.isEmpty())
        {
            log.info("Aucune photo associée a son menu item");
            return false;
        }

        return true;
    }
}
