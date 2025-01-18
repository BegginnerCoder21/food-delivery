package com.api.food_delivery_api.service;

import com.api.food_delivery_api.dto.PaymentRequest;
import com.api.food_delivery_api.enumeration.PaymentStatus;

public interface PaymentService {

    PaymentStatus pay(PaymentRequest paymentRequest);

    String inquiry(String orderId);
}
