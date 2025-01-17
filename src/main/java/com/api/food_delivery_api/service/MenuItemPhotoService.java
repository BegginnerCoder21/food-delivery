package com.api.food_delivery_api.service;

import com.api.food_delivery_api.dto.MenuItemPhotoRequest;
import com.api.food_delivery_api.dto.MenuItemPhotoResponse;
import com.api.food_delivery_api.dto.MenuItemRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MenuItemPhotoService {

    List<MenuItemPhotoResponse> upload(MultipartFile[] files, MenuItemPhotoRequest menuItemPhotoRequest) throws IOException;
    MenuItemPhotoResponse update(Long id, MenuItemPhotoRequest menuItemPhotoRequest);
    MenuItemPhotoResponse getById(Long id);
    List<MenuItemPhotoResponse> getAll();
    void delete(Long id);

}
