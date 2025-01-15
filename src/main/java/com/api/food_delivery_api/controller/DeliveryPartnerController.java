package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.DeliveryPartnerRequest;
import com.api.food_delivery_api.dto.DeliveryPartnerResponse;
import com.api.food_delivery_api.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food-delivery/")
public class DeliveryPartnerController {

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;

    @PostMapping("/creating-delivery-partner")
    public DeliveryPartnerResponse create(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest)
    {
        return this.deliveryPartnerService.create(deliveryPartnerRequest);
    }

    @PutMapping("/udpating-delivery-partner/{id}")
    public DeliveryPartnerResponse udpate(@PathVariable Long id, @RequestBody DeliveryPartnerRequest deliveryPartnerRequest)
    {
        return this.deliveryPartnerService.update(id, deliveryPartnerRequest);
    }
}
