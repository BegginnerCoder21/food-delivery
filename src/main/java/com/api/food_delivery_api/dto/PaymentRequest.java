package com.api.food_delivery_api.dto;

import com.api.food_delivery_api.enumeration.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private Long orderId;
}
