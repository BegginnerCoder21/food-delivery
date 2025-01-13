package com.api.food_delivery_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "feedback")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FeedBack extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200)
    private String comment;
    private double rating;
    private Date feedbackDate;
    private Long restaurantId;
    private Long orderId;
    private Long deliveryId;
    private Long userId;
}
