package com.api.food_delivery_api.dto;

import com.api.food_delivery_api.enumeration.VehicleType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPartnerRequest {

    private String firstname;
    private String lastname;
    private String username;
    private String phoneNumber;
    private String gender;
    private String password;
    private Date dateOfBirth;
    private String email;
    private String address;
    private VehicleType vehicleType;
    private boolean available;
}
