package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.MenuItemPhotoRequest;
import com.api.food_delivery_api.dto.MenuItemPhotoResponse;
import com.api.food_delivery_api.service.MenuItemPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/food-delivery/menu-item-photo")
public class MenuItemPhotoController{


    @Autowired
    private MenuItemPhotoService menuItemPhotoService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuItemPhotoResponse> upload(@RequestParam("files") MultipartFile[] files,MenuItemPhotoRequest menuItemPhotoRequest) throws IOException {
        log.info("men item photo request {}",menuItemPhotoRequest);
        return this.menuItemPhotoService.upload(files, menuItemPhotoRequest);
    }

    @PutMapping("/{id}")
    public MenuItemPhotoResponse update(@PathVariable Long id, @RequestBody MenuItemPhotoRequest menuItemPhotoRequest) {
        return this.menuItemPhotoService.update(id, menuItemPhotoRequest);
    }

    @GetMapping("/{id}")
    public MenuItemPhotoResponse getById(@PathVariable Long id) {
        return this.menuItemPhotoService.getById(id);
    }

    @GetMapping("")
    public List<MenuItemPhotoResponse> getAll() {
        return this.menuItemPhotoService.getAll();
    }

    @DeleteMapping("")
    public void delete(@PathVariable Long id) {

        this.menuItemPhotoService.delete(id);
    }
}
