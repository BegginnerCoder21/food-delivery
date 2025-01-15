package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.DeliveryPartnerRequest;
import com.api.food_delivery_api.dto.DeliveryPartnerResponse;
import com.api.food_delivery_api.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food-delivery/")
public class DeliveryPartnerController {

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;

    @PostMapping("/creating-delivery-partner")
    public DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest)
    {
        return this.deliveryPartnerService.create(deliveryPartnerRequest);
    }
}
