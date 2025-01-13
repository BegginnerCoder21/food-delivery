package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
