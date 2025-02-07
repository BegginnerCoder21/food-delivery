package com.api.food_delivery_api.service;

import com.api.food_delivery_api.dto.UserRequest;
import com.api.food_delivery_api.dto.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest userRequest0);
    UserResponse update(Long id, UserRequest userRequest);
    ResponseEntity delete(Long id);
    UserResponse findById(Long id);
    List<UserResponse> allUser();

}
