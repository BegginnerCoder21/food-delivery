package com.api.food_delivery_api.dto;

import com.api.food_delivery_api.enumeration.UserType;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String phoneNumber;
    private String address;
    private String email;
    private UserType userType;
    private String status;
}
