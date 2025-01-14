package com.api.food_delivery_api.dto;

import com.api.food_delivery_api.entity.User;
import com.api.food_delivery_api.enumeration.UserType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String username;
    private String firstname;
    private String lastname;
    private String gender;
    private Date dateOfBirth;
    private String phoneNumber;
    private String address;
    private String email;
    private UserType userType;
    private String status;
    private DeviceRequest deviceRequest;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DeviceRequest{

        private String deviceId;
        private String deviceType;
        private String deviceModel;
        private String osVersion;
        private String appVersion;
        private boolean isTrusted;

    }
}
