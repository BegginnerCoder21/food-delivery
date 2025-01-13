package com.api.food_delivery_api.entity;

import com.api.food_delivery_api.enumeration.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "delivery")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIME)
    private Date pickupTime = new Date();
    @Temporal(TemporalType.TIME)
    private Date deliveryTime = new Date();
    private String pickupAddess;
    private  String deliveryAddress;
    private double deliveryFee;
    private DeliveryStatus deliveryStatus;
    private Long orderId;
    private Long deliveryPartnerId;
}
