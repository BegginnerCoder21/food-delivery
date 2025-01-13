package com.api.food_delivery_api.entity;

import com.api.food_delivery_api.enumeration.NotificationChannel;
import com.api.food_delivery_api.enumeration.NotificationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private NotificationType notificationType;
    private NotificationChannel notificationChannel;
    private boolean read;
    private Long deviceId;
    private Long userId;
}
