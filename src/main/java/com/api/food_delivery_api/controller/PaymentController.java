package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.PaymentRequest;
import com.api.food_delivery_api.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food-delivery/")
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping("/pay")
    public String payment(@RequestBody PaymentRequest paymentRequest)
    {
        return this.payment(paymentRequest);
    }
}
