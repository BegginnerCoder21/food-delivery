package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.UserRequest;
import com.api.food_delivery_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food-delivery/")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register-user")
    public ResponseEntity create(@RequestBody UserRequest userRequest)
    {
        try {

            this.userService.create(userRequest);

            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
