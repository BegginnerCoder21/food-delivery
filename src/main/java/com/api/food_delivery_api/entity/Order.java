package com.api.food_delivery_api.entity;

import com.api.food_delivery_api.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate = new Date();
    @Temporal(TemporalType.TIME)
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
