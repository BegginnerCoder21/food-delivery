package com.api.food_delivery_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "device")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
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
