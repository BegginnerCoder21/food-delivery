package com.api.food_delivery_api.entity;


import com.api.food_delivery_api.enumeration.PaymentMethod;
import com.api.food_delivery_api.enumeration.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "payment")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String description;
    private Long orderId;
}
