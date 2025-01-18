package com.api.food_delivery_api.dto;

import com.api.food_delivery_api.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private Date orderDate;
    private Date deliveryTime;
    private BigDecimal totalAmount;
    private double deliveryFee;
    private double tax;
    private double restaurantRating;
    private double deliveryRating;
    private OrderStatus orderStatus;
    private Long userId;
    private Long restaurantId;
    private Long orderItemId;
    private Long paymentId;
    private Long deliveryId;
    private Long deliveryPartnerId;
}
