package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.PaymentRequest;
import com.api.food_delivery_api.enumeration.PaymentStatus;
import com.api.food_delivery_api.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food-delivery/")
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping("/pay")
    public PaymentStatus payment(@RequestBody PaymentRequest paymentRequest)
    {
        return this.paymentService.pay(paymentRequest);
    }
}
