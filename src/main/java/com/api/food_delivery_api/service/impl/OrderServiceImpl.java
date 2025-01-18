package com.api.food_delivery_api.service.impl;

import com.api.food_delivery_api.dto.OrderRequest;
import com.api.food_delivery_api.dto.OrderResponse;
import com.api.food_delivery_api.entity.User;
import com.api.food_delivery_api.exeption.UserNotFoundErrorException;
import com.api.food_delivery_api.repository.UserRepository;
import com.api.food_delivery_api.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderResponse create(OrderRequest orderRequest) {

        boolean userExist = this.userRepository.existsById(orderRequest.getUserId());

        if(!userExist)
        {
            log.warn("Aucun utilisateur trouvé pour l'id {}", orderRequest.getUserId());
            throw new UserNotFoundErrorException("Utilisateur non trouvé!");
        }

        User foundUser = this.userRepository.findById(orderRequest.getUserId()).get();


        return null;
    }
}
