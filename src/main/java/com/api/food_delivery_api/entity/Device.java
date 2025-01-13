package com.api.food_delivery_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "device")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Device extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deviceId;
    private String deviceType;
    private String deviceModel;
    private String osVersion;
    private String appVersion;
    private String lastLogin;
    private boolean isTrusted;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
