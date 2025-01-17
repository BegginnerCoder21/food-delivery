package com.api.food_delivery_api.config;

import com.api.food_delivery_api.service.handler.menuitemphoto.MenuItemPhotoHandler;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    MenuItemPhotoHandler menuItemPhotoHandler()
    {
        return new MenuItemPhotoHandler();
    }

}
