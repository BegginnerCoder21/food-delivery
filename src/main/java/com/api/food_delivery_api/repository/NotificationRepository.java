package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
