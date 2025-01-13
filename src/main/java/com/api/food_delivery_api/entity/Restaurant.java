package com.api.food_delivery_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "restaurant")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String category;
    private double rating;
    private String phoneNumber;
    private String location;
    private String logoUrl;
    @Temporal(TemporalType.TIME)
    private Date openTime;
    @Temporal(TemporalType.TIME)
    private Date closeTime;
}
