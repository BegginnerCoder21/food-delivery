package com.api.food_delivery_api.entity;

import com.api.food_delivery_api.enumeration.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Table(name = "delivery_partner")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPartner extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    private String gender;
    private String password;
    private Date dateOfBirth;
    private String email;
    private String address;
    private VehicleType vehicleType;
    private boolean available;
}
