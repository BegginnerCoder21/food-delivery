package com.api.food_delivery_api.service;

import com.api.food_delivery_api.dto.PaymentRequest;

public interface PaymentService {

    String pay(PaymentRequest paymentRequest);

    String inquiry(String orderId);
}
