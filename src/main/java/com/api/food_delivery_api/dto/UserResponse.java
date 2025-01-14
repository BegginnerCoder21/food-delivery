package com.api.food_delivery_api.dto;

import com.api.food_delivery_api.enumeration.UserType;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    private List<DeviceResponse> deviceResponse;


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DeviceResponse{

        private Long id;
        private String deviceId;
        private String deviceType;
        private String deviceModel;
        private String osVersion;
        private String appVersion;
        private boolean isTrusted;

    }
}
